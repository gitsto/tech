
home_dir=`pwd`

clean="clean"
clean_eclipse="eclipse:clean"
eclipse_eclipse="eclipse:eclipse"
inst="install"
# if compilation must be skipped used "-Dskip.test" instead
skipTest="-Dmaven.test.skip=true"

cmd="mvn $clean $clean_eclipse $eclipse_eclipse $inst $skipTest"

cd $WORKSPACE/$DKE_VERSION/

echo "cmd = $cmd"
$cmd

# go back to home_dir.
cd $home_dir

