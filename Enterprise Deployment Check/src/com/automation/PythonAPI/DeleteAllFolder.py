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
parser.add_argument("-f", "--FolderName", type=str)
parser.add_argument("-b", "--BaseFolderName", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
FolderName = args.FolderName
BaseFolderName = args.BaseFolderName

"""portalUrl = "https://qaclient6.esri.com/portal"
userName = "admin"
password = "portaladmin"
BaseFolderName="Folder_"
"""


def Delete_AllFolders(portalUrl, userName, password, BaseFolderName):

    try:
        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me

        if user:
            print("Login Success", flush=True)
        folders = list(user.folders)
        folder_found = False
        for folder in folders:
            try:
                # ? Extract folder name (string parsing - working in your env)
                folder_title = str(folder).split("Folder: ")[1].split(" Owner")[0]
               
                if folder_title.startswith(BaseFolderName):
                    folder_found = True
                    print(f"Deleting folder: {folder_title}", flush=True)
                    # ? NEW METHOD (Correct way)
                    folder.delete()
                    print(f"Deleted: {folder_title}", flush=True)
            except Exception as e:
                print(f"Error reading/deleting folder: {str(e)}", flush=True)

            if not folder_found:
                print(f"No folder found starting with '{BaseFolderName}'", flush=True)
                print("STATUS:PASS", flush=True)

    except Exception as e:
        print("ERROR:", str(e), flush=True)
        print("STATUS:FAIL", flush=True)


# Call function
Delete_AllFolders(portalUrl, userName, password, BaseFolderName) 
