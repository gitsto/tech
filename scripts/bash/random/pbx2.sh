

echo "pbx script!"
#sshpass -p 'WElcome12#' ssh qeuser@10.204.180.52 "sudo rm /tmp/*csv"

sshpass -p 'WElcome12#' ssh qeuser@10.204.180.52 "./pbx.sh"

echo "exit"
exit 0

sshpass -p 'WElcome12#' scp qeuser@10.204.180.52 "./pbx.sh"
sshpass -p 'WElcome12#' scp qeuser@10.204.180.52:/tmp/pbx*.csv ~/Downloads/


