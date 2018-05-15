#!/bin/bash
set -euo pipefail
IFS=$'\n\t'
SCRIPTDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
DATADIR="data"
MODE="-ti"

if [ "${1:-}" == "travis" ]; then
    MODE="-d"
fi

#
# NOTE: This script requires docker
#

# Check to see if the data dir exists, and bail if it doesn't
if [ ! -d "${SCRIPTDIR}/${DATADIR}" ]; then
  echo "Stub data directory not found. (should have been at [${SCRIPTDIR}/${DATADIR}])"
  echo "Please get the API stub data first! (ie run get-fd-stub-data.sh)"
  exit 1
fi


# Delete any existing wiremock container and start a new one
docker rm -f wm > /dev/null || true
echo "Launching Wiremock"
docker run "${MODE}" --name wm \
                     -p 8080:8080 \
                     -v "${SCRIPTDIR}/${DATADIR}:/home/wiremock" \
                     -u "$(id -u):$(id -g)" \
                     rodolpheche/wiremock:2.17.0-alpine --verbose
echo "Done!"
