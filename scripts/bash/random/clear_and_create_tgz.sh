echo "rm /tmp/dke* ; mvna ; crdv"
rm /tmp/dke*
#./mvn_all.sh ; ./create_tgz.sh ; 
./mvn_all.sh ; ./build_dev_release_V4.2.sh ;
if [ $# == 1 ] 
then
	./install_tgz.sh $1
	exit
fi

