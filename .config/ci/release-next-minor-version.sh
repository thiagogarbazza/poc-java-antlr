#!/bin/bash
set -euo pipefail

current_version=$(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]')
major_value=$(echo "$current_version" | cut -d. -f1)
minor_value=$(echo "$current_version" | cut -d. -f2)
next_version="$major_value.$(($minor_value+1)).0-SNAPSHOT"

echo -e "
  current_version: $current_version
  next_version: $next_version
"

./mvnw \
  --batch-mode \
  -DdevelopmentVersion="$next_version" \
  release:update-versions

if  [[ $(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]') == "$next_version" ]]; then
  echo "Version updated successfully to $next_version"
else
  echo "Failed to update version to $next_version"
  exit 1
fi

git commit --all --message "release: prepare next minor version iteration $next_version"
