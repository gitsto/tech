#!/bin/sh

if [ $# != 2 ]
then
    echo "usage: $0 [turret_number] [action_number]"
    echo "example: $0 t1|t2|all kill|reboot|shutdown"
    exit
fi

# parameters
turret=$1
action=$2

# vars
t1=icastor #192.168.46.185
t2=ipollux #192.168.46.186
act=""
cmd=""
tur1=""
tur2=""

# init the hosts
if [ $turret = "t1" ]
then 
tur1=$t1
elif [ $turret = "t2" ]
then
tur1=$t2
elif [ $turret = "all" ]
then
tur1=$t1
tur2=$t2
else 
echo "no turret found, exit."
exit
fi

turrets="$tur1 $tur2"
echo "turrets=$turrets"

# init the action
if [ $action = "kill" ]
then 
act="killall -9 java"
elif [ $action = "reboot" ]
then
act="reboot"
elif [ $action = "shutdown" ]
then
act="shutdown"
else 
echo "no action found, exit."
exit
fi

for i in $turrets
do
cmd="ssh root@$i \"$act\""
echo "cmd=$cmd"
$cmd
done
echo "done."
