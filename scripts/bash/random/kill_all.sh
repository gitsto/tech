
cd ~/bin/scripts
echo "SHUTDOWN DKE processes (without chrome) >>>>>>>>>>>>>>>>>>>>>>"
cmd="kill -9 `ps -ax | grep dke | grep -v chrome | awk '{print $1}'`"
echo "$cmd"
$cmd

echo "check with a grep on dke ..."
ps -ax | grep dke

echo "SHUTDOWN T1 >>>>>>>>>>>>>>>>>>>>>>"
./turret_kill_java.sh t1

echo "SHUTDOWN T2 >>>>>>>>>>>>>>>>>>>>>>"
./turret_kill_java.sh t2
