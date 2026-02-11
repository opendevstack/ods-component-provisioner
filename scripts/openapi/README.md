# TODO Adapt this README to component provisioner

## About
This directory contains the required files in order to generate:
- A Bitbucket Spring REST client
- A Component Catalog Spring Boot REST API server

## Prerrequisites
Before running any scripts for code generation, please do the following:

1. Install nodejs, npm and npx
2. Install the OpenAPI generator: https://openapi-generator.tech/docs/installation
3. If required, alter the generator config file(s) to customize.

## Contents
The contents of this directory are as follows:

```
scripts/openapi
├── generated-sources   # temp directory for generated sources
│   ├── ...
├── generator-configs   # modify these config files to customize generated code
│   ├── generator-bitbucket-client.yaml 
│   └── generator-component_provisioner-server.yaml
├── img             # images for this README
├── generate.sh     # run this to generate the sources
├── openapitools.json   # metainfo about the openapi-generator tool
├── README.md       # this file
├── update-bb-api.sh        # update Bitbucket client src with the last generated
└── update-provisioner-api.sh   # update Provisioner API src with the last generated one
```

## Howto

To generate both the code for the Bitbucket Spring REST client and the Component Catalog Spring Boot REST API: 

Run: `$ ./generate.sh run`

To update the AAP client with the last generated code:

Run: `$ ./update-awx-client-api.sh run`

To update the Spring REST client for component catalog with the last generated code:

Run: `$ ./update-component_catalog-client-api.sh run`

To update the Component Provisioner Spring Boot REST API with the last generated code:

Run: `$ ./update-provisioner-server-api.sh run`
