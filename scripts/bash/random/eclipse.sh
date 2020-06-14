#!/bin/bash

set -e

folder=$HOME/bin/eclipse/
echo "folder=$folder"
cd $folder
echo "./eclipse -vm /usr/bin"
./eclipse -vm /usr/bin &

#echo "./eclipse -vm $JAVA_HOME/bin"
#./eclipse -vm $JAVA_HOME/bin &
