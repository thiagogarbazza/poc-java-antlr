#!/bin/bash
set -euo pipefail

./mvnw \
  --batch-mode \
  --fail-fast \
  --update-snapshots \
  --activate-profiles release \
  -DskipTests=true \
  clean package
