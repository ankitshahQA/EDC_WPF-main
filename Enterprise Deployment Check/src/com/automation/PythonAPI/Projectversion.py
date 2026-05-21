from arcgis.gis import GIS
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("-p", "--portalUrl", type=str)
parser.add_argument("-u", "--userName", type=str)
parser.add_argument("-w", "--password", type=str)

args = parser.parse_args()
portalUrl = args.portalUrl
userName = args.userName
password = args.password
"""
portalUrl ="https://qa-k8s-nfs.esri.com/arcgis"
userName="aeAdmin"
password="Esri.4.GIS"
"""
try:
    gis = GIS(portalUrl, userName, password, verify_cert=False)
    print("Connected:", gis)
    #print("Properties:", gis.properties)  # ?? IMPORTANT DEBUG
    deployment = gis.properties.get("portalDeploymentType", "UNKNOWN")
    version = gis.properties.get("enterpriseVersion", "UNKNOWN")
    print(f"FINALVERSION:{deployment}_{version}", flush=True)

except Exception as e:
    print("ERROR:", str(e))
