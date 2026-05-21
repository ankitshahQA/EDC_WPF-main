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



"""from arcgis.gis import GIS

portalUrl = "https://qaclient14.esri.com/portal"
userName = "portaladmin"
password = "portaladmin"
"""

def delete_scene_items(portalUrl, userName, password):
    final_status = "PASS"
    proceed = False

    try:
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me
        print(f"Connected to GIS as {user.username}")

        # ?? Detect environment
        try:
            servers = gis.admin.servers.list()
            for server in servers:
                version = str(server.properties.currentVersion)
                #print(f"Server URL: {server.url}")
                print(f"Server Version: {version}")

                try:
                    major_minor = ".".join(version.split(".")[:2])
                    version_number = float(major_minor)

                    if version_number >= 11.0:
                        proceed = True
                        
                    
                except Exception:
                    print("Version parsing failed, assuming Kubernetes.")
                    proceed = True

                
        except Exception:
            # ? Kubernetes fallback
            print("Kubernetes environment detected (no servers API).")
            proceed = True

        # ? Skip if condition not met
        if not proceed:
            print("Server version condition not met. Skipping deletion.")
            return "SKIPPED"

        # ? Proceed with deletion
        print("\nChecking ROOT folder...")
        root_items = user.items()
        #print("Total items:", len(root_items))
        print(f"\nChecking ROOT folder...{root_items}")
        items = gis.content.search(query=f"owner:{user.username}", max_items=100)

        for item in items:
            try:
                print(f"Checking: {item.title} -> {item.type}")
                if item.type in ["Scene Layer", "Scene Service", "Scene Layer Package", "Scene Package"]:
                    print(f"Deleting: {item.title}")
                    item.delete()

            except Exception as e:
                print(f"Failed: {item.title} -> {str(e)}")
                final_status = "FAIL"
                
    except Exception as e:
        print("Python exception occurred:", str(e))
        final_status = "FAIL"

    return final_status


# ? Call function
status = delete_scene_items(portalUrl, userName, password)
print("FINAL STATUS:", status)