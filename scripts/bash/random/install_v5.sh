#!/bin/bash

platform=$1

if [ $# != 1 ] 
then
    echo "\nDescription : "
    echo "this script will install to the correct platform" 
    echo "usage: $0 [platform] (platforme=int2|int1|dev2|dev1|rec1|rec2..."
    exit
fi

echo "clean /tmp/dke* files"
sudo rm -rf /tmp/dke*

echo "build new tgz"
$SCRIPTS/build_dev_release_V5.sh

echo "cd repo"
cd $REPO_INSTALL

echo "deploy on platform = ${platform}"
sudo ./install.sh ${platform} /tmp/dke-Dev-*.tgz /tmp/dkeVT-*.tgz

exit 0

