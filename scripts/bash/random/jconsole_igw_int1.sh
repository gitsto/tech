
echo "IGWs:"
jmx_port=50010
igw1=192.168.48.11
echo ${igw1}:${jmx_port}
igw2=192.168.48.42
echo ${igw2}:${jmx_port}
igw3=192.168.48.74
echo ${igw3}:${jmx_port}

jconsole ${igw1}:${jmx_port} &
jconsole ${igw2}:${jmx_port} &
jconsole ${igw3}:${jmx_port} &

