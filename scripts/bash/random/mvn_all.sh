
home_dir=`pwd`

clean="clean"
clean_eclipse="eclipse:clean"
eclipse_eclipse="eclipse:eclipse"
inst="install"
# if compilation must be skipped used "-Dskip.test" instead
skipTest="-DskipTests"

cmd="mvn $clean $clean_eclipse $eclipse_eclipse $inst $skipTest"

echo "cd $WORKSPACE/$DKE_VERSION/"
cd $WORKSPACE/$DKE_VERSION/

echo "cmd = $cmd"
$cmd

# go back to home_dir.
cd $home_dir

