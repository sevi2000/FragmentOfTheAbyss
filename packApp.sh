#!/usr/bin/bash
echo "Generating jar artifact"
./gradlew assembleDist
echo "Getting jar artifact path"
jar_path="$(./gradlew printJarPath | grep -i 'Logged JAR name'|cut -d ":" -f 2 | tr -d " ")"
echo "Getting app name form jar artifact"
jar_name=$(basename $jar_path)
app_name=$(echo "$jar_name" | cut -d "." -f 1) 
echo "Creating build dir"
build_dir="${app_name}-build-dir"

mkdir -pv $build_dir/DEBIAN
mkdir -pv $build_dir/usr/bin
mkdir -pv $build_dir/usr/share/myapp
mkdir -pv $build_dir/usr/share/icons/hicolor/48x48/apps
mkdir -pv $build_dir/usr/share/applications

cp -v $jar_path myapp/usr/share/myapp/$jar_name

cd $build_dir/usr/bin
cat << EOF > launch.sh
#!/usr/bin/bash
java -jar /usr/share/myapp/$jar_name
EOF
chmod +x launch.sh
cd -
cp icon.png $build_dir/usr/share/icons/hicolor/48x48/apps/
