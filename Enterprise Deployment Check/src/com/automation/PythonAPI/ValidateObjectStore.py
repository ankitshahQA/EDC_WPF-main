from arcgis.gis import GIS
import sys
import argparse


parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
"""
PORTAL_URL = "https://qaclient6.esri.com/portal"
USERNAME = "admin"
PASSWORD = "portaladmin"
"""

def check_precondition():
    try:
        gis = GIS(portalUrl, userName, password)

        # -----------------------------
        # Traditional ArcGIS Enterprise Check
        # -----------------------------
        if hasattr(gis.admin, "servers"):
            servers = gis.admin.servers.list()

            if not servers:
                print("No servers found")
                return False

            for server in servers:
                datastores = server.datastores.list()

                for ds in datastores:
                    props = ds.properties

                    ds_type = str(props.get("type", "")).lower()
                    info = props.get("info", {})
                    ds_feature = str(info.get("dsFeature", "")).lower()

                    #print(f"Checking datastore: {ds_type}")

                    # Check for Object Store or Tile Cache
                    if ds_type == "objectstore" or ds_feature == "tilecache":
                        #print("Required datastore found")
                        return True

        print("Required datastore NOT found")
        return False

    except Exception as e:
        print(f"ERROR: {e}")
        return False


if __name__ == "__main__":
    if check_precondition():
        print("PRECONDITION_STATUS:TRUE")
        sys.exit(0)
    else:
        print("PRECONDITION_STATUS:FALSE")
        sys.exit(1)