import argparse
import requests

# Argument parser
parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password


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


# ?? Get portal version (replacement of server version logic)
def get_portal_version(token):
    try:
        url = f"{portalUrl}/sharing/rest/portals/self"
        params = {"f": "json", "token": token}

        res = requests.get(url, params=params, verify=False)
        data = res.json()

        version = data.get("currentVersion", "0")
        print(f"Portal Version: {version}")

        major_minor = ".".join(str(version).split(".")[:2])
        return float(major_minor)

    except Exception:
        print("Version check failed. Assuming Kubernetes.")
        return 11.0


# ?? Search items
def search_items(token):
    url = f"{portalUrl}/sharing/rest/search"
    params = {
        "q": f"owner:{userName}",
        "num": 100,
        "f": "json",
        "token": token
    }

    res = requests.get(url, params=params, verify=False)
    return res.json().get("results", [])


# ?? Delete item
def delete_item(token, item_id):
    url = f"{portalUrl}/sharing/rest/content/users/{userName}/items/{item_id}/delete"
    payload = {"f": "json", "token": token}

    res = requests.post(url, data=payload, verify=False)
    return res.json()


# ?? Main logic
def delete_scene_items():
    final_status = "PASS"

    try:
        token = generate_token()

        if not token:
            print("Login failed")
            return "FAIL"

        print(f"Connected to GIS as {userName}")

        # ?? Version check
        version_number = get_portal_version(token)

        if version_number < 11.0:
            print("Server version condition not met. Skipping deletion.")
            return "SKIPPED"

        print("\nChecking items...")

        items = search_items(token)

        for item in items:
            try:
                title = item.get("title")
                item_type = item.get("type")
                item_id = item.get("id")

                print(f"Checking: {title} -> {item_type}")

                if item_type in [
                    "Scene Layer",
                    "Scene Service",
                    "Scene Layer Package",
                    "Scene Package"
                ]:
                    print(f"Deleting: {title}")

                    result = delete_item(token, item_id)

                    if result.get("success"):
                        print(f"Deleted: {title}")
                    else:
                        print(f"Failed: {title} -> {result}")
                        final_status = "FAIL"

            except Exception as e:
                print(f"Error processing item: {str(e)}")
                final_status = "FAIL"

    except Exception as e:
        print("Python exception occurred:", str(e))
        final_status = "FAIL"

    return final_status


# Run
status = delete_scene_items()
print("FINAL STATUS:", status)