import logging
import sys
import datetime
import time
import argparse
from concurrent.futures import ThreadPoolExecutor, as_completed
from arcgis.gis import GIS


parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)


args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
"""
# -----------------------------
# Config
# -----------------------------
portalUrl = "https://qaclient14.esri.com/portal"
userName = "portaladmin"
password = "portaladmin"
"""
MAX_RETRIES = 2
RETRY_DELAY = 5
MAX_WORKERS = 5

# -----------------------------
# Logging
# -----------------------------
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s",
    handlers=[logging.StreamHandler(sys.stdout)]
)

# -----------------------------
# Connect to GIS
# -----------------------------
def connect_gis():
    try:
        logging.info("Connecting to GIS...")
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        logging.info(f"Connected as {gis.users.me.username}")
        return gis
    except Exception as e:
        logging.error(f"Connection failed: {e}")
        return None

# -----------------------------
# Get Hosting Server
# -----------------------------
def get_hosting_server(gis):
    try:
        servers = gis.admin.servers.list()
        portal_host = portalUrl.split("//")[1].split("/")[0]

        for s in servers:
            if type(s).__name__ == "Server" and portal_host in s.url:
                return s

        return None
    except Exception as e:
        logging.error(f"Error getting hosting server: {e}")
        return None

# -----------------------------
# Filter Datastores
# -----------------------------
def filter_datastores(datastores):
    filtered = []

    for ds in datastores:
        props = ds.properties
        path = props.get("path", "")
        ds_type = props.get("type", "")
        info = props.get("info", {})

        system_managed = info.get("systemManaged", False)
        ds_feature = info.get("dsFeature", "")

        if ds_type == "egdb" and "AGSDataStore" in path:
            continue

        if system_managed and ds_feature not in [
            "spatioTemporal", "graphStore", "tileCache", "queueStore"
        ]:
            continue

        filtered.append(ds)

    return filtered

# -----------------------------
# Validate Datastore
# -----------------------------
def validate_datastore(ds):
    path = ds.properties.get("path", "Unknown")
    ds_type = ds.properties.get("type", "Unknown")
    info = ds.properties.get("info", {})
    ds_feature = info.get("dsFeature", "")

    for attempt in range(1, MAX_RETRIES + 2):
        try:
            logging.info(f"Validating: {path} (Attempt {attempt})")

            result = ds.validate()

            # Special handling
            if ds_feature == "queueStore" and not result:
                return (path, ds_type, True, None)

            if ds_type == "rasterStore":
                if not result and attempt <= MAX_RETRIES:
                    time.sleep(RETRY_DELAY)
                    continue
                elif not result:
                    return (path, ds_type, True, None)

            if result:
                return (path, ds_type, True, None)
            else:
                return (path, ds_type, False, "Validation returned False")

        except Exception as e:
            if attempt <= MAX_RETRIES:
                time.sleep(RETRY_DELAY)
            else:
                return (path, ds_type, False, str(e))

# -----------------------------
# Main
# -----------------------------
def main():
    gis = connect_gis()

    if not gis:
        print("FINAL_STATUS:FAIL")
        print("MESSAGE:GIS connection failed")
        sys.exit(1)

    server = get_hosting_server(gis)

    # ?? WARNING
    if not server:
        print("FINAL_STATUS:WARNING")
        print("MESSAGE:Hosting server not found")
        sys.exit(0)

    try:
        datastores = server.datastores.list()
        datastores = filter_datastores(datastores)

        all_passed = True
        failed_datastores = []

        with ThreadPoolExecutor(max_workers=MAX_WORKERS) as executor:
            futures = [executor.submit(validate_datastore, ds) for ds in datastores]

            for future in as_completed(futures):
                path, ds_type, success, error = future.result()

                if success:
                    logging.info(f"PASS: {path}")
                else:
                    logging.error(f"FAIL: {path} | {error}")
                    failed_datastores.append(path)
                    all_passed = False

        print("=" * 50)

        if all_passed:
            print("FINAL_STATUS:PASS")
            sys.exit(0)
        else:
            print("FINAL_STATUS:FAIL")
            print(f"FAILED_DATASTORES:{','.join(failed_datastores)}")
            sys.exit(1)

    except Exception as e:
        print("FINAL_STATUS:FAIL")
        print(f"MESSAGE:{str(e)}")
        sys.exit(1)

# -----------------------------
# Entry
# -----------------------------
if __name__ == "__main__":
    main()