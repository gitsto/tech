echo 1 > /proc/sys/net/ipv6/conf/all/disable_ipv6
sudo mkdir -p /var/run/ntp/
sudo touch /var/run/ntp/ntp.sync
cat /proc/sys/net/ipv6/conf/all/disable_ipv6
exit
