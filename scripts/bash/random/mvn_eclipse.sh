
home_dir=`pwd`

clean="clean"
clean_eclipse="eclipse:clean"
eclipse_eclipse="eclipse:eclipse"

cmd="mvn $clean $clean_eclipse $eclipse_eclipse"

cd $WORKSPACE/$DKE_VERSION/

echo "cmd = $cmd"
$cmd

# go back to home_dir.
cd $home_dir

