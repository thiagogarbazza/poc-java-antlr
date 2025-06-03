#!/bin/bash
set -euo pipefail

./mvnw \
  --batch-mode \
  --fail-fast \
  org.jacoco:jacoco-maven-plugin:prepare-agent \
  org.jacoco:jacoco-maven-plugin:prepare-agent-integration \
  -DCI=true \
  -Dsurefire.printSummary=true \
  -Dsurefire.skipAfterFailureCount=1 \
  -Dfailsafe.printSummary=true \
  -Dfailsafe.skipAfterFailureCount=1 \
  -Daspectj.skip=true \
  verify

./mvnw \
  --batch-mode \
  org.jacoco:jacoco-maven-plugin:report
