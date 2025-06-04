#!/bin/bash
set -euo pipefail

./mvnw \
  --batch-mode \
  -DignoreSnapshots=true \
  release:clean \
  release:prepare
