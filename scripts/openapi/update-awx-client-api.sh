#!/bin/bash

# This script updates the Bitbucket REST client sources
AWX_CLIENT_SRC_FROM="./generated-sources/openapi-client-awx/src/%s/java/com/boehringer/componentprovisioner/client/awx/v2"
AWX_CLIENT_SRC_TO="../../src/%s/java/com/boehringer/componentprovisioner/client/awx/v2"

[ "$1" != "run" ] && echo "Usage: $(basename $0) run - Update AWX REST client sources" && exit 1

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

echo "[INFO] Updating AWX REST client sources..."

rm -irf $(printf "$AWX_CLIENT_SRC_TO" "main") && echo "[INFO] Deleted $(printf "$AWX_CLIENT_SRC_TO" "main")"
cp -r  $(printf "$AWX_CLIENT_SRC_FROM" "main") $(dirname $(printf "$AWX_CLIENT_SRC_TO" "main")) && echo "[INFO] Updated $(printf "$AWX_CLIENT_SRC_TO" "main")"
