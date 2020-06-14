#!/bin/sh

echo "START TURRET >>>>>>>>>>>>>>>>>>>"
echo "WORKSPACE=$$WORKSPACE"

my_host=`hostname -i` #(192.168.46.26)
echo "my_host=$my_host"
#tur1=icastor #192.168.46.185
#tur1=192.168.46.185

#tur1=ipollux #192.168.46.49
tur1=192.168.46.49

echo "tur1=$tur1"
#tur2=ipollux #192.168.46.186 
#tur2=192.168.46.186
#tur2=icasrtor #192.168.46.50
tur2=192.168.46.50
echo "tur2=$tur2"

if [ $# != 2 ] 
then
    echo "\nDescription : "
    echo "this script will connect on the remote turret, the make a mount and launch the gui_for_dev script"
    echo "important: the SAS applications must be launched first !"
    echo "usage: $0 [turret] [run_mode]" 
    echo "dke_version: the folder name in the workspace, ex dke, dke-V1.3, ..."
    echo "turret: t1 ($tur1) or t2 ($tur2)"
    echo "example: $0 dke t1 n (r for remode debug or n for normal run)"
    exit
fi

turret=$1
remote_host=""
run_mode=$2

if [ $turret = "t1" ]
then 
remote_host=$tur1
elif [ $turret = "t2" ]
then
remote_host=$tur2
else 
echo "no turret found, exit."
exit
fi

if [ $run_mode = "r" ]
then 
run_mode="-rdebug"
else
run_mode=""
fi

cmd="./gui_FOR_DEV.sh $run_mode -addr=$MY_MCAST_IP -repl_mcast_addr=$MY_REPL_IP"
echo "cmd=$cmd"
cmd_stop_term="sv stop terminal"

echo "connect as root, and mount the fs"
ssh root@$remote_host <<EOF
echo "topsy_version:"
cat /config/current/RELEASE
echo "touch /var/run/ntp/ntp.sync"
touch /var/run/ntp/ntp.sync
echo "check if NFS client is enabled (Y) and if terminal is in loaded mode (D)... (if not ssh on turret then edit /config/sysconfig/services) AND REBOOT!!!"
javer -s 7
grep -i ENABLE_NFSCLIENT /config/sysconfig/services
grep -i ENABLE_TERMINAL /config/sysconfig/services
echo "mount the WORKSPACE=$WORKSPACE from $my_host on the turret."
mount -r $my_host:$WORKSPACE /mnt
pwd
cd /mnt/$DKE_VERSION/launcher/script/
pwd
echo "Stop old terminal jvm: $cmd_stop_term"
$cmd_stop_term
echo "start GUI: $cmd"
su turret -c "exec $cmd"
EOF
echo "done."

