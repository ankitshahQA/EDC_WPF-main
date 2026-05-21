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


"""portalUrl = "https://qaclient6.esri.com/portal"
userName = "admin"
password = "portaladmin"
"""
def delete_tile_layers(portalUrl, userName, password):
    final_status = "PASS"

    try:
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me
        print(f"Connected to GIS as {user.username}")

        # ?? Get all items (root + folders)
        items = gis.content.search(
            query=f"owner:{user.username}",
            max_items=1000
        )

        print(f"Total items found: {len(items)}")

        for item in items:
            try:
                print(f"Checking: {item.title} -> {item.type}")

                # ? Condition: Only Tile Layers
                if item.type in ["Tile Layer", "Tile Service", "Vector Tile Layer","Vector Tile Service","Vector Tile Package"]:
                    print(f"Deleting Tile Layer: {item.title}")
                    item.delete()

            except Exception as e:
                print(f"Failed: {item.title} -> {str(e)}")
                final_status = "FAIL"

    except Exception as e:
        print("Python exception occurred:", str(e))
        final_status = "FAIL"

    return final_status


# ? Call function
status = delete_tile_layers(portalUrl, userName, password)
print("FINAL STATUS:", status)