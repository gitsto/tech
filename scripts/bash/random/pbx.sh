#!/bin/sh

#user=qeuser
#remote_host=10.204.180.52
# scp  qeuser@10.204.180.52:/home/qeuser/

#echo "connect $user@$remote_host and perform mysql request"
#ssh $user@$remote_host <<EOF
#pwd
#EOF
#echo "done."


mysql -u dunkin -p dunkin -A < pbx_20160523.sql

