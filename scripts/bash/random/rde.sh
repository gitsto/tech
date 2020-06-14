user=administrator
password=Admin_94120
resolution=1916x1032
keyboard=fr

if [ $# != 1 ] 
then
    echo "\nDescription : "
    echo "this script will connect on the remote desktop" 
    echo "usage: $0 [platform] (platforme=int2|int1|dev2|dev1|rec1|rec3"
    exit
fi

domain=$1

if [ $1 == 'int2' ]
then 
	envip=192.168.48.101
fi
if [ $1 == 'int1' ]
then 
	envip=192.168.48.5
fi
if [ $1 == 'dev1' ]
then
	envip=192.168.46.133
fi
if [ $1 == 'dev2' ]
then
	envip=192.168.49.27
fi
if [ $1 == 'rec1' ]
then
	envip=192.168.47.5
fi
if [ $1 == 'rec2' ]
then
	envip=192.168.47.69
fi
if [ $1 == 'rec3' ]
then
	envip=192.168.47.162
fi
if [ $1 == 'simu112' ]
then	
	envip=192.168.49.112
fi
if [ $1 == 'perf' ]
then	
	envip=172.29.189.68
fi
if [ $1 == 'ntr168' ]
then	
	envip=172.29.182.168
	user=administrator
	password=recorder
fi
if [ $1 == 'ntr170' ]
then	
	envip=172.29.182.170
	user=administrator
	password=recorder
fi
if [ $1 == 'ntr171' ]
then	
	envip=172.29.182.171
	user=administrator
	password=recorder
fi
if [ $1 == 'xpress' ]
then
	envip=172.29.184.228
	user=administrateur
	password=suplab
fi
if [ $1 == 'dke3' ]
then
	envip=172.29.184.52
	user=administrateur
	password=suplab
fi
if [ $1 == 'dke4' ]
then
	envip=172.29.186.130
	user=administrateur
	password=suplab
fi
if [ $1 == 'dsTruc' ]
then 
	envip=172.16.26.166
	user=administrateur
	password=suplab
fi
if [ $1 == 'rec194' ]
then 
	envip=172.16.26.194
	password=recorder
fi
if [ $1 == 'ver' ]
then 
	envip=192.168.49.28
	user=version
fi
if [ $1 == 'slk1' ]
then 
	envip=192.168.49.58
fi
if [ $1 == 'slk2' ]
then 
	envip=192.168.49.59
fi
if [ $1 == 'cmqe' ]
then 
	envip=172.29.182.101
fi
if [ $1 == 'cmat' ]
then
	envip=192.168.49.24
fi
if [ $1 == 'qentr26' ]
then
	envip=172.29.66.26
	user=CT-Admin
	password=CTrec0rd
fi
if [ $1 == 'qentr45' ]
then
	envip=172.29.66.45
	user=CT-Admin
	password=CTrec0rd
fi
# s2 doc ref 2 (entreprise architect, 
if [ $1 == 'win' ]
then 
	envip=192.168.49.42
	domain=INTETRALI
	user=stefan.sto
	password=1Pc2016jun
fi

if [ $1 == 'ntrin' ]
then 
	envip=10.13.114.42
	domain=IPC_AMERICA
	user=CT-Admin
	password=CTrec0rd
fi



echo ""
echo "envip=$envip"
echo "domain=$domain"
echo "user=$user"
echo "password=$password"
echo "keyboard=$keyboard"
echo "resolution=$resolution"
echo ""
rdesktop -u $domain\\$user -p $password -k $keyboard -g $resolution $envip &

