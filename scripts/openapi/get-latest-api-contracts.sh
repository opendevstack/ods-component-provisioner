#!/bin/bash

set -euo pipefail

CATALOG_URL="https://raw.githubusercontent.com/opendevstack/ods-component-catalog/refs/heads/master/openapi/openapi-component_catalog-v1.0.0.yaml"
PROJECTS_INFO_URL="https://raw.githubusercontent.com/opendevstack/ods-projects-info-service/refs/heads/master/openapi/openapi-projects-info-service-v1.0.0.yaml"


CATALOG_LOCAL="$(dirname "$0")/../../openapi/openapi-component_catalog-v1.0.0.yaml"
PROJECTS_INFO_LOCAL="$(dirname "$0")/../../openapi/openapi-projects-info-service-v1.0.0.yaml"

curl -fsSL "$CATALOG_URL" -o "$CATALOG_LOCAL"
echo "Updated: $CATALOG_LOCAL"
curl -fsSL "$PROJECTS_INFO_URL" -o "$PROJECTS_INFO_LOCAL"
echo "Updated: $PROJECTS_INFO_LOCAL"
