

dest="~/Downloads/"

if [ $# != 1 ] 
then
    echo "\nDescription : "
    echo "this script will connect on the remote platform and copy the csv_file to the ${dest}" 
    echo "usage: $0 [csv_file] (ex: pbx_20160519.csv)"
    exit
fi

csv_file=$1

sshpass -p 'WElcome12#' scp qeuser@10.204.180.52:/tmp/${csv_file} ${dest}
