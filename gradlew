#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
GRADLE_HOME="${GRADLE_HOME:-$DIR/.gradle/wrapper}"
if [ ! -f "$DIR/gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "Gradle wrapper JAR not found. Please run 'gradle wrapper' to generate it."
    exit 1
fi
exec java -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
