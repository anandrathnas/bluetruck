#!/bin/bash

gpg --batch --gen-key <<EOF
%no-protection
Key-Type: RSA
Key-Length: 4096
Name-Real: Anand Rathna
Name-Email: anand@rathnas.com
Expire-Date: 0
EOF



if [[ $? -ne 0 ]]; then
    echo "Error: GPG key generation failed."
    exit 1
fi
echo "GPG key generated."



KEY_ID=$(gpg --list-keys --with-colons | awk -F: '/^pub/ {print $5}' | head -n 1)
if [[ -z "$KEY_ID" ]]; then
    echo "Error: Key ID extraction failed."
    exit 1
fi
echo "Generated key: $KEY_ID"



gpg --export-secret-keys --armor "$KEY_ID" > "${KEY_ID}-private.asc"
echo "Private key exported to ${KEY_ID}-private.asc"
base64 -i "${KEY_ID}-private.asc" -o "${KEY_ID}-private.b64"
echo "Private key base64'd to ${KEY_ID}-private.b64"



gpg --export --armor "$KEY_ID" > "${KEY_ID}-public.asc"
echo "Public key exported to ${KEY_ID}-public.asc"
gpg --keyserver keys.openpgp.org --send-keys "$KEY_ID"
echo "Public key sent to keys.openpgp.org"



echo "Copy the contents of ${KEY_ID}-private.b64 to GPG_PRIVATE_KEY for github actions"