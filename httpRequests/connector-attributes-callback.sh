#!/bin/bash

curl -X POST \
 --cacert ../ssl/server/server.crt \
 --cert ../ssl/client/client1.p12:123456 \
 --cert-type p12 \
 -H 'content-type: application/json' \
 -d @callback-coreGetCredentials.json  \
 https://localhost:8443/api/v1/connectors/24fd52e9-5e2a-480c-b5ff-450d7d8a184a/callback
