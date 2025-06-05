#!/bin/bash
set -euo pipefail

current_version=$(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]')
major_value=$(echo "$current_version" | cut -d. -f1)
next_version="$(($major_value+1)).0.0-SNAPSHOT"

echo -e "
  current_version: $current_version
  next_version: $next_version
"

echo "Updating version in pom.xml to $next_version"
./mvnw versions:set -DnewVersion=$next_version

if  [[ $(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]') == "$next_version" ]]; then
  echo "Version updated successfully to $next_version"
else
  echo "Failed to update version to $next_version"
  exit 1
fi

git commit --all --message "release: prepare next major version iteration $next_version"
