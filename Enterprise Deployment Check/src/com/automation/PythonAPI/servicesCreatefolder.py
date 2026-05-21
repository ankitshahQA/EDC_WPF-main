import argparse
from arcgis.gis import GIS

# Argument parser
"""parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)
parser.add_argument("-f", "--FolderName", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
FolderName = args.FolderName
"""

from arcgis.gis import GIS

portalUrl = "https://qaclient6.esri.com/portal"
userName = "admin"
password = "portaladmin"
FolderName="FOLDER_TEST1"

def validate_login_and_create_folder(portalUrl, userName, password, FolderName):
    try:
        # Connect to GIS
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me
        print(f"Connected to GIS as {user.username}")

        # ?? Try to get server version (skip for Kubernetes)
        try:
            servers = gis.admin.servers.list()
            for server in servers:
                print("Server Version:", server.properties.currentVersion)
        except Exception:
            print("Kubernetes environment detected. Skipping server check.")

        # ?? Folder creation
        if user:
            try:
                if not FolderName or FolderName.strip() == "":
                    print("Folder name is empty. Cannot create folder.", flush=True)
                    print("STATUS:FAIL", flush=True)
                    return False

                folder = gis.content.folders.create(FolderName)
                print(f"Folder created Successfully: {FolderName}", flush=True)
                print("STATUS:PASS", flush=True)
                return True

            except Exception as folder_error:
                print("Folder creation failed:", str(folder_error), flush=True)
                print("STATUS:FAIL", flush=True)
                return False

        else:
            print("User not found", flush=True)
            print("STATUS:FAIL", flush=True)
            return False

    except Exception as e:
        print("Python exception occurred:", str(e), flush=True)
        print("STATUS:FAIL", flush=True)
        return False


# Call function
validate_login_and_create_folder(portalUrl, userName, password, FolderName)