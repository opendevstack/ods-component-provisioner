#!/bin/bash

# This script updates the Bitbucket REST client sources
CC_CLIENT_SRC_FROM="./generated-sources/openapi-client-componentcatalog/src/%s/java/com/boehringer/componentprovisioner/client/componentcatalog/v1"
CC_CLIENT_SRC_TO="../../src/%s/java/com/boehringer/componentprovisioner/client/componentcatalog/v1"

[ "$1" != "run" ] && echo "Usage: $(basename $0) run - Update Component Catalog REST client sources" && exit 1

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

echo "[INFO] Updating Component Catalog REST client sources..."

rm -irf $(printf "$CC_CLIENT_SRC_TO" "main") && echo "[INFO] Deleted $(printf "$CC_CLIENT_SRC_TO" "main")"
cp -r  $(printf "$CC_CLIENT_SRC_FROM" "main") $(dirname $(printf "$CC_CLIENT_SRC_TO" "main")) && echo "[INFO] Updated $(printf "$CC_CLIENT_SRC_TO" "main")"
