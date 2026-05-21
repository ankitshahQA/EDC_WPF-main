import argparse
import datetime
from arcgis.gis import GIS
from arcgis.geometry import Geometry

import arcgis._impl._geometry_engine as geom
geom._force_python_engine = True
import os
parser = argparse.ArgumentParser()

parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)
parser.add_argument("-f", "--FolderName", type=str)
parser.add_argument("-i", "--Item_LayerName", type=str)
"""
args = parser.parse_args()
portalUrl = args.portalUrl or os.getenv("PORTAL_URL") or "https://qa-k8s-nfs.esri.com/arcgis"
userName = args.userName or os.getenv("PORTAL_USERNAME") or "aeAdmin"
password = args.password or os.getenv("PORTAL_PASSWORD") or "Esri.4.GIS"
FolderName = args.FolderName or os.getenv("FOLDER_NAME") or "Folder_20260320022125"
Item_LayerName = args.Item_LayerName or os.getenv("ITEM_NAME") or "Feature_20260323034700"
"""
args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
FolderName = args.FolderName
Item_LayerName = args.Item_LayerName

"""
print("Starting job with config:", flush=True)
print(f"Portal: {portalUrl}", flush=True)
print(f"User: {userName}", flush=True)
print(f"Folder: {FolderName}", flush=True)
print(f"Search: {Item_LayerName}", flush=True)
"""


# -------------------------------
# Normalize function
# -------------------------------
def normalize(text):
    if not text:
        return ""
    return text.lower().replace("?", "").strip()

# -------------------------------
# Main Function (SEARCH ??????)
# -------------------------------
def move_items_by_title(portalUrl, userName, password, FolderName, Item_LayerName):

    try:
        print("Connecting to GIS...", flush=True)

        gis = GIS(portalUrl, userName, password, verify_cert=False)
        user = gis.users.me

        print(f"Connected to GIS as {user.username}", flush=True)

        target = normalize(Item_LayerName)
        print(f"Searching for: {target}", flush=True)

        # -------------------------------
        # Validate Folder
        # -------------------------------
        folder_names = []

        for f in user.folders:
            name = getattr(f, "title", None) or getattr(f, "name", None)
            if name:
                folder_names.append(name)

        print(f"Available folders: {folder_names}", flush=True)

        if FolderName not in folder_names:
            print(f"ERROR: Folder '{FolderName}' does not exist.", flush=True)
            print("STATUS:FAIL", flush=True)
            return "FAIL"

        # -------------------------------
        # SEARCH ???? portal ???
        # -------------------------------
        print("Searching items across portal...", flush=True)

        search_items = gis.content.search(
            query=Item_LayerName,
            max_items=1000
        )

        print(f"Items found: {len(search_items)}", flush=True)

        moved = 0

        # -------------------------------
        # Loop and Move
        # -------------------------------
        for item in search_items:
            try:
                item_title = normalize(item.title)

                print(f"Checking: {item.title}", flush=True)

                if target in item_title:

                    print(f"Matched: {item.title} -> {item.type}", flush=True)

                    if item.ownerFolder != FolderName:
                        item.move(folder=FolderName)
                        print(f"Moved to folder: {FolderName}", flush=True)
                        moved += 1
                    else:
                        print("Already in target folder", flush=True)

            except Exception as e:
                print(f"Failed: {item.title} -> {str(e)}", flush=True)

        # -------------------------------
        # Final Status
        # -------------------------------
        if moved == 0:
            print("No matching items found.", flush=True)
            print("STATUS:SKIPPED", flush=True)
            return "SKIPPED"

        print(f"Total items moved: {moved}", flush=True)
        print("STATUS:PASS", flush=True)
        return "PASS"

    except Exception as e:
        print("Python exception occurred:", str(e), flush=True)
        print("STATUS:FAIL", flush=True)
        return "FAIL"


# -------------------------------
# MAIN ENTRY (Kubernetes friendly)
# -------------------------------
if __name__ == "__main__":

    

    status = move_items_by_title(
        portalUrl,
        userName,
        password,
        FolderName,
        Item_LayerName
    )

    print("FINAL STATUS:", status)

    # Kubernetes job success/fail handling
    if status == "FAIL":
        exit(1)
    else:
        exit(0)