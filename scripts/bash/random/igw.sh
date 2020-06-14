#!/bin/sh

script_dir="$WORKSPACE/$DKE_VERSION/launcher/script/"
cd $script_dir
pwd

cmd="./start_mdc_and_maa_and_cab_and_igwaudio_FOR_DEV.sh --mcast_addr=$MY_MCAST_IP --repl_mcast_addr=$MY_REPL_IP"
echo "cmd=$cmd"
$cmd
