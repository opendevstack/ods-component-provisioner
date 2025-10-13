#!/bin/bash

# Usage: ./generate.sh [run]

CP_SPEC="generator-configs/generator-componentprovisioner-server.yaml"
AWX_SPEC="generator-configs/generator-awx-client.yaml"
CC_SPEC="generator-configs/generator-componentcatalog-client.yaml"

function usage() {
    echo "Usage: $(basename $0) run"
    echo "This script generates the sources for the following:
        - An AWX Spring REST client
        - A Component Catalog Spring REST client
        - a Component Provisioner Spring Boot REST server
        The generated sources will be placed under the directory: ./generated-sources
    "
}

[ "$1" != "run" ] && usage && exit 1

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

which npm && which npx || { echo "ERROR: some required software packages are missing, take a look at the README.md for installation instructions"; exit 1; }

# Clean up previously generated sources
echo "[INFO] Cleaning up previously generated sources..."
rm -rf generated-sources

## Backend - Component Provisioner
# AWX REST client
echo "[INFO] Generating AWX REST client from configuration: $AWX_SPEC ..."
# Skipping validation for the spec, but we are ignoring irrelevant errors here
npx openapi-generator-cli generate --skip-validate-spec -c $AWX_SPEC > /dev/null

# Component Catalog REST client
echo "[INFO] Generating Component Catalog REST client from configuration: $CC_SPEC ..."
# Skipping validation for the spec, but we are ignoring irrelevant errors here
npx openapi-generator-cli generate --skip-validate-spec -c $CC_SPEC > /dev/null

# Component Provisioner REST API
echo "[INFO] Generating Component Provisioner REST server from configuration: $CP_SPEC ..."
npx openapi-generator-cli generate -c $CP_SPEC > /dev/null
