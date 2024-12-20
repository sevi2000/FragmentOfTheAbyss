#!/usr/bin/bash
jar_path="$(./gradlew printJarPath | grep -i 'Logged JAR name'|cut -d ":" -f 2 | tr -d " ")"
app_name=$(basename $jar_path | cut -d "." -f 1) 
mkdir "${app_name}-build-dir"
#jpackage --input myapp/ --name MyApp --main-jar "$1" --type deb
