import argparse
import requests

# Argument parser
parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)
parser.add_argument("-b", "--BaseFolderName", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
BaseFolderName = args.BaseFolderName


# ?? Generate Token
def generate_token():
    url = f"{portalUrl}/sharing/rest/generateToken"
    payload = {
        "username": userName,
        "password": password,
        "client": "referer",
        "referer": portalUrl,
        "f": "json"
    }

    res = requests.post(url, data=payload, verify=False)
    data = res.json()

    return data.get("token")


# ?? Get folders
def get_folders(token):
    url = f"{portalUrl}/sharing/rest/content/users/{userName}"
    params = {"f": "json", "token": token}
    res = requests.get(url, params=params, verify=False)
    return res.json().get("folders", [])


# ?? Get items inside folder
def get_items(token, folder_name):
    url = f"{portalUrl}/sharing/rest/content/users/{userName}/{folder_name}"
    params = {"f": "json", "token": token}
    res = requests.get(url, params=params, verify=False)
    return res.json().get("items", [])


# ?? Delete item
def delete_item(token, item_id):
    url = f"{portalUrl}/sharing/rest/content/users/{userName}/items/{item_id}/delete"
    payload = {"f": "json", "token": token}
    res = requests.post(url, data=payload, verify=False)
    return res.json()


# ?? Delete folder
def delete_folder(token, folder_name):
    url = f"{portalUrl}/sharing/rest/content/users/{userName}/{folder_name}/delete"
    payload = {"f": "json", "token": token}
    res = requests.post(url, data=payload, verify=False)
    return res.json()


# ?? Main logic
def Delete_AllFolders_And_Items():
    try:
        token = generate_token()

        if not token:
            print("Login failed", flush=True)
            print("STATUS:FAIL", flush=True)
            return

        print("Login Success", flush=True)

        folders = get_folders(token)
        folder_found = False

        for folder in folders:
            folder_name = folder.get("title")

            if folder_name.startswith(BaseFolderName):
                folder_found = True
                print(f"\nProcessing Folder: {folder_name}", flush=True)

                # ?? Step 1: Delete all items inside folder
                items = get_items(token, folder_name)

                for item in items:
                    item_id = item.get("id")
                    item_title = item.get("title")

                    print(f"Deleting item: {item_title}", flush=True)
                    result = delete_item(token, item_id)

                    if result.get("success"):
                        print(f"Deleted item: {item_title}", flush=True)
                    else:
                        print(f"Failed to delete item: {item_title}", flush=True)

                # ?? Step 2: Delete folder
                print(f"Deleting folder: {folder_name}", flush=True)
                folder_result = delete_folder(token, folder_name)

                if folder_result.get("success"):
                    print(f"Deleted folder: {folder_name}", flush=True)
                else:
                    print(f"Failed to delete folder: {folder_name}", flush=True)

        if not folder_found:
            print(f"No folder found starting with '{BaseFolderName}'", flush=True)

        print("\nSTATUS:PASS", flush=True)

    except Exception as e:
        print("ERROR:", str(e), flush=True)
        print("STATUS:FAIL", flush=True)


# Run
Delete_AllFolders_And_Items()