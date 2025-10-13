#!/bin/bash
# This script updates the Component Provisioner REST API sources
CP_API_SRC_FROM="./generated-sources/openapi-server-componentprovisioner/src/%s/java/com/boehringer/componentprovisioner/server"
CP_API_SRC_TO="../../src/%s/java/com/boehringer/componentprovisioner/server"

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

[ "$1" != "run" ] && echo "Usage: $(basename $0) run - Update Component Provisioner backend REST API sources" && exit 1

echo "[INFO] Updating Component Provisioner backend REST API sources..."

rm -irf $(printf "$CP_API_SRC_TO/api" "main") && echo "[INFO] Deleted $(printf "$CP_API_SRC_TO/api" "main")"
rm -irf $(printf "$CP_API_SRC_TO/model" "main") && echo "[INFO] Deleted $(printf "$CP_API_SRC_TO/model" "main")"

cp -r  $(printf "$CP_API_SRC_FROM/api" "main") $(printf "$CP_API_SRC_TO" "main") && echo "[INFO] Updated $(printf "$CP_API_SRC_TO/api" "main")"
cp -r  $(printf "$CP_API_SRC_FROM/model" "main") $(printf "$CP_API_SRC_TO" "main") && echo "[INFO] Updated $(printf "$CP_API_SRC_TO/model" "main")"
