#!/bin/bash
set -euo pipefail

current_version=$(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]')
major_value=$(echo "$current_version" | cut -d. -f1)
minor_value=$(echo "$current_version" | cut -d. -f2)
patch_value=$(echo "$current_version" | cut -d. -f3 | cut -d- -f1)
closed_version="$major_value.$minor_value.$patch_value"
next_version="$major_value.$minor_value.$(($patch_value+1))-SNAPSHOT"

echo -e "
  current_version: $current_version
  closed_version: $closed_version
  next_version: $next_version
"

echo "Updating version in pom.xml to $closed_version"
./mvnw versions:set -DnewVersion=$closed_version

if  [[ $(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]') == "$closed_version" ]]; then
  echo "Version updated successfully to $closed_version"
else
  echo "Failed to update version to $closed_version"
  exit 1
fi

git commit --all --message "release: $closed_version"

git tag --force --message "release: $closed_version" "$closed_version"

echo "Updating version in pom.xml to $next_version"
./mvnw versions:set -DnewVersion=$next_version

if  [[ $(sed -ne '5,10 s/<version>\(.*\)<\/version>/\1/p' pom.xml | tr -d '[:space:]') == "$next_version" ]]; then
  echo "Version updated successfully to $next_version"
else
  echo "Failed to update version to $next_version"
  exit 1
fi

git commit --all --message "release: prepare next patch version iteration $next_version"
