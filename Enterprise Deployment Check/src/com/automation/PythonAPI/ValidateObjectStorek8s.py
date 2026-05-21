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
portalUrl = "https://qa-k8s-nfs.esri.com/arcgis"
userName = "aeAdmin"
password = "Esri.4.GIS"
"""

# -----------------------------
# 1. Health Check (K8s safe)
# -----------------------------
def is_system_healthy(gis):
    try:
        _ = gis.properties
        #print("Portal API OK")

        _ = gis.content.search(query="*", max_items=1)
        #print("Content API OK")

        return True

    except Exception as e:
        print(f"Health check failed: {e}")
        return False


# -----------------------------
# 2. Object Store Presence (Indirect)
# -----------------------------
def is_object_store_present(gis):
    try:
        # If content API works ? backend storage exists
        items = gis.content.search(query="*", max_items=1)

        if items is not None:
           # print("Object Store (indirect) ? PRESENT")
            return True

        print("Object Store (indirect) ? NOT confirmed")
        return False

    except Exception as e:
        print(f"Object store check failed: {e}")
        return False


# -----------------------------
# 3. Strong Validation (Publish Test)
# -----------------------------
def test_object_store(gis):
    try:
        #print("Running object store publish test...")

        csv_data = "x,y\n77.1,28.6\n77.2,28.7"

        item = gis.content.add(
            {"title": "temp_test_layer", "type": "CSV"},
            data=csv_data
        )

        published = item.publish()
        #print("Publish test successful")

        # Cleanup
        published.delete()
        item.delete()

        return True

    except Exception as e:
        print(f"Publish test failed: {e}")
        return False


# -----------------------------
# MAIN VALIDATION
# -----------------------------
def check_precondition(deep_test=False):
    try:
        gis = GIS(portalUrl, userName, password)

        # Step 1: Health
        if not is_system_healthy(gis):
            return False

        # Step 2: Object store presence (indirect)
        if not is_object_store_present(gis):
            return False

        # Step 3: Optional deep validation
        if deep_test:
            if not test_object_store(gis):
                return False

        return True

    except Exception as e:
        print(f"ERROR: {e}")
        return False


# -----------------------------
# ENTRY POINT
# -----------------------------
if __name__ == "__main__":
    try:
        deep_test = True if len(sys.argv) > 1 and sys.argv[1] == "deep" else False

        if check_precondition(deep_test):
            print("PRECONDITION_STATUS:TRUE")
            sys.exit(0)
        else:
            print("PRECONDITION_STATUS:FALSE")
            sys.exit(1)

    except Exception as e:
        print(f"ERROR: {e}")
        print("PRECONDITION_STATUS:FALSE")
        sys.exit(1)