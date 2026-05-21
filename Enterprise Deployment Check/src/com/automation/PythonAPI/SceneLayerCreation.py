#from arcgis.gis import GIS
from pathlib import Path
import sys
import argparse
import datetime
def get_gis(portalUrl, userName, password):
    from arcgis.gis import GIS
    return GIS(portalUrl, userName, password, verify_cert=False)
# ==============================
# CONFIGURATION
# ==============================
"""
portalUrl = "https://qaclient14.esri.com/portal"
userName = "portaladmin"
password = "portaladmin"
FolderName="EDC_Automation"
SCENE_TITLE = "Automated Web Scene"
SCENE_LAYER="Automated Scene Layer"

"""
parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)
parser.add_argument("-f", "--FolderName", type=str)
parser.add_argument("-sl", "--SCENE_LAYER", type=str)
parser.add_argument("-s", "--SCENE_TITLE", type=str)


args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
FolderName = args.FolderName
SCENE_TITLE = args.SCENE_TITLE
SCENE_LAYER = args.SCENE_LAYER

SCENE_SUMMARY = "Created using ArcGIS API for Python"
SCENE_TAGS = ["automation", "webscene", "3D"]

# ==============================
# PATH
# ==============================
"""
base_path = Path(__file__).resolve()
project_root = base_path.parents[4]
scene_package_path = project_root / "Input" / "ChateauFare.slpk"
"""
base_path = Path(__file__).resolve()
for parent in base_path.parents:
    matches = list(parent.rglob("ChateauFare.slpk"))
    if matches:
        scene_package_path = matches[0]
        break

print("?? Scene Path:", scene_package_path)
if not scene_package_path.exists():
    print(f"? Scene package not found: {scene_package_path}")
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# AUTH
# ==============================
try:
    gis = get_gis(portalUrl, userName, password)
    print("? Connected to:", gis.properties.portalHostname)
except Exception as e:
    print("? Authentication Failed:", str(e))
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# FOLDER CHECK
# ==============================
try:
    user = gis.users.me

    folder_exists = any(
        getattr(f, "title", None) == FolderName or
        getattr(f, "name", None) == FolderName or
        (isinstance(f, dict) and f.get("title") == FolderName)
        for f in user.folders
    )

    if not folder_exists:
        print(f"? Folder '{FolderName}' not found.")
        print("\nFINAL STATUS:FAIL")
        sys.exit(1)

    print(f"? Folder '{FolderName}' exists")

except Exception as e:
    print("? Folder validation failed:", str(e))
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# INIT VARIABLES (for summary)
# ==============================
slpk_item = None
published_item = None
web_scene_item = None

# ==============================
# UPLOAD
# ==============================
try:
    print("? Uploading Scene Package...")

    slpk_item = gis.content.add(
        item_properties={
            "title": SCENE_LAYER ,
            "type": "Scene Package",
            "tags": SCENE_TAGS,
            "snippet": SCENE_SUMMARY
            
        },
        data=str(scene_package_path),
        folder=FolderName
    )

    print("? Uploaded:", slpk_item.title)

except Exception as e:
    print("? Upload Failed:", str(e))
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# PUBLISH
# ==============================
try:
    print("?? Publishing Scene Layer...")

    published_item = slpk_item.publish()

    print("? Scene Layer:", published_item.title)

except Exception as e:
    print("? Publish Failed:", str(e))
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# CREATE WEB SCENE
# ==============================
try:
    print("?? Creating Web Scene...")

    web_scene_json = {
        "operationalLayers": [
            {
                "id": published_item.id,
                "layerType": "ArcGISSceneServiceLayer",
                "url": published_item.url,
                "title": published_item.title
            }
        ],
        "baseMap": {"baseMapLayers": [], "title": "BaseMap"}
    }

    web_scene_item = gis.content.add(
        item_properties={
            "title": SCENE_TITLE,
            "type": "Web Scene",
            "snippet": SCENE_SUMMARY,
            "tags": SCENE_TAGS,
            "text": web_scene_json,
             "layer": SCENE_LAYER
        },
        folder=FolderName
    )

    print("? Web Scene:", web_scene_item.title)

except Exception as e:
    print("? Web Scene Failed:", str(e))
    print("\nFINAL STATUS:FAIL")
    sys.exit(1)

# ==============================
# FINAL SUCCESS OUTPUT
# ==============================
print("FINAL STATUS: PASS")
print("===================================")

if slpk_item:
    print("?? Scene Package:", slpk_item.title)

if published_item:
    print("?? Scene Layer:", published_item.title)

if web_scene_item:
    print("?? Web Scene:", web_scene_item.title)

print("===================================")

sys.exit(0)