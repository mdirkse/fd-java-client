#!/bin/bash
set -euo pipefail
IFS=$'\n\t'
SCRIPTDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
DATADIR="data"

#
# NOTE: This script requires curl and docker
#

if [ -z "${FD_AUTH_TOKEN+x}" ]; then
  echo "FD_AUTH_TOKEN must be set to your Football-Data API token!";
  exit 1;
fi

# Delete the data dir if it exists and create a new one
rm -fr "${SCRIPTDIR:?}/${DATADIR}"
mkdir "${SCRIPTDIR}/${DATADIR}"

# Delete any existing wiremock container and start a new one
docker rm -f wm > /dev/null || true
echo "Launching Wiremock"
docker run -d --name wm \
              -p 8080:8080 \
              -v "${SCRIPTDIR}/${DATADIR}:/home/wiremock" \
              -u "$(id -u):$(id -g)" \
              rodolpheche/wiremock:2.17.0-alpine > /dev/null

printf "Waiting for Wiremock to start "
until curl --output /dev/null --silent --head --fail http://localhost:8080/__admin; do
    printf '.'
    sleep 0.5
done
printf " done.\n"

# Tell wiremock to start proxying and recording requests
echo "Putting Wiremock in 'record' mode."
curl -s -X POST --data "{ \"targetBaseUrl\": \"http://staging-api.football-data.org\" }" http://localhost:8080/__admin/recordings/start > /dev/null

declare -a stub_urls=(
    "/v2/areas"
    "/v2/areas?areas=164,2"
    "/v2/areas/164"
    "/v2/competitions"
    "/v2/competitions/1"
    "/v2/competitions/1/matches"
    "/v2/competitions/1/matches?stage=REGULAR_SEASON&status=FINISHED&matchday=1"
    "/v2/competitions/1/teams"
    "/v2/teams/1"
    "/v2/matches"
    "/v2/matches?dateFrom=2018-04-15&dateTo=2018-04-15"
    "/v2/matches/313"
)

# Should add "/v2/competitions?areas=1%2C2%2C3" but it's broken

echo "Doing ${#stub_urls[@]} requests to get stub data:"
for i in "${stub_urls[@]}"; do
    printf "\t Fetching [%s]\n" "${i}"
   curl -s -X GET -H "X-Response-Control: minified" -H "X-Auth-Token: ${FD_AUTH_TOKEN}" "http://localhost:8080${i}" > /dev/null
done

# Stop recording
curl -s -X POST http://localhost:8080/__admin/recordings/stop > /dev/null

echo "Done!"
