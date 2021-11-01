.RECIPEPREFIX +=
.DEFAULT_GOAL := clean

version-for-updates:
  mvn org.codehaus.mojo:versions-maven-plugin:2.7:display-dependency-updates
