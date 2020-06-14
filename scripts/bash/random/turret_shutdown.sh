#!/bin/sh

echo "SHUTDOWN TURRET >>>>>>>>>>>>>>>>>>>"
my_host=`hostname -i` #(192.168.46.26)
echo "my_host=$my_host"
tur1=icastor #192.168.46.185
echo "tur1=$tur1"
tur2=ipollux #192.168.46.186
echo "tur2=$tur2"

if [ $# != 1 ]
then
    echo "\nDescription : "
    echo "this script will connect on the remote turret, and shutdown it "
    echo "usage: $0 [turret]" 
    echo "turret: t1 ($tur1) or t2 ($tur2)"
    echo "example: $0 t1" 
    exit
fi

turret=$1
remote_host=""

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

echo "connect as root, and shutdown"
ssh root@$remote_host <<EOF
echo "topsy_version"
cat /config/current/RELEASE
shutdown
EOF
echo "done."
