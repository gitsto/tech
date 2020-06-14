


if [ $# != 1 ] 
then
    echo "\nDescription : "
    echo "this script will install to the correct platform" 
    echo "usage: $0 [platform] (platforme=int2|int1|dev2|dev1|rec1|rec3"
    exit
fi


cd $WORKSPACE/repo/
pwd
platform=$1
sudo ./install.sh $platform /tmp/*.tgz

