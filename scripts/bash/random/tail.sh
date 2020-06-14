
file=all.log
log_dir=/tmp/dke/log

host=$1
type=$2
nb=$3

if [ $# == 3 ]
then
	echo "3 args"
	cmd="ssh ${host} tail -f ${log_dir}/${type}-${nb}/${file}"
elif [ $# == 2 ]
then
	echo "2 args"
	cmd="ssh ${host} tail -f ${log_dir}/${type}/${file}"
else
	echo "usage $0 [host] [type (cab, tur, vt)] [number (If type is vt)]"
	exit
fi

echo "$cmd"

$cmd
