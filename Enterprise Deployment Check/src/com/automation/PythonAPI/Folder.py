import argparse
import requests

# Argument parser
parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)
parser.add_argument("-f", "--FolderName", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
FolderName = args.FolderName


"""from arcgis.gis import GIS

portalUrl = "https://qaclient6.esri.com/portal"
userName = "admin"
password = "portaladmin"
FolderName="FOLDER_TEST5"
"""
def generate_token(portalUrl, username, password):
    url = f"{portalUrl}/sharing/rest/generateToken"
    
    payload = {
        "username": username,
        "password": password,
        "client": "referer",
        "referer": portalUrl,
        "f": "json"
    }

    response = requests.post(url, data=payload, verify=False)
    data = response.json()

    if "token" in data:
        return data["token"]
    else:
        print("Token generation failed:", data)
        return None


def create_folder(portalUrl, username, token, folder_name):
    url = f"{portalUrl}/sharing/rest/content/users/{username}/createFolder"

    payload = {
        "title": folder_name,
        "f": "json",
        "token": token
    }

    response = requests.post(url, data=payload, verify=False)
    return response.json()


def main():
    try:
        if not FolderName or FolderName.strip() == "":
            print("Folder name is empty. Cannot create folder.", flush=True)
            print("STATUS:FAIL", flush=True)
            return

        print("Generating token...")
        token = generate_token(portalUrl, userName, password)

        if not token:
            print("Login failed", flush=True)
            print("STATUS:FAIL", flush=True)
            return

        print("Login successful")

        result = create_folder(portalUrl, userName, token, FolderName)

        if "success" in result and result["success"]:
            print(f"Folder created Successfully: {FolderName}", flush=True)
            print("STATUS:PASS", flush=True)
        else:
            print("Folder creation failed:", result, flush=True)
            print("STATUS:FAIL", flush=True)

    except Exception as e:
        print("Python exception occurred:", str(e), flush=True)
        print("STATUS:FAIL", flush=True)


if __name__ == "__main__":
    main()