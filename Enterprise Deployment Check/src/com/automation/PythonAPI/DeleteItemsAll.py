import argparse
import datetime
from arcgis.gis import GIS
from arcgis.geometry import Geometry

# Force API to use pure Python geometry engine
import arcgis._impl._geometry_engine as geom
geom._force_python_engine = True
import arcgis.gis._impl._portalpy as pp
print(dir(pp))
from arcgis.gis import GIS

# Argument parser
parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password


"""
portalUrl = "https://qaclient3.esri.com/portal"
userName = "admin"
password = "esri.agp"
"""
def delete_automation_items(portalUrl, userName, password):
    final_status = "PASS"

    try:
        # Connect to GIS
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me
        print(f"Connected to GIS as: {user.username}")

        # ?? Optimized search (only titles starting with Automation)
        items = gis.content.search(
            query=f'owner:{user.username} AND title:Automation*',
            max_items=1000
        )

        print(f"Total matching items found: {len(items)}")

        for item in items:
            try:
                print(f"Checking: {item.title} -> {item.type}")

                # ? Double safety check
                if item.title and item.title.lower().startswith("automation"):
                    print(f"Deleting Item: {item.title}")
                    item.delete()
                else:
                    print(f"Skipped: {item.title}")

            except Exception as e:
                print(f"Failed to delete: {item.title} -> {str(e)}")
                final_status = "FAIL"

    except Exception as e:
        print("Python exception occurred:", str(e))
        final_status = "FAIL"

    return final_status


# ?? Execute
status = delete_automation_items(portalUrl, userName, password)
print("FINAL STATUS:", status)