#!/bin/bash

set -e

folder=$HOME/bin/eclipse-juno/
echo "folder=$folder"
cd $folder
echo "./eclipse -vm $JAVA_HOME/bin"
#./eclipse -vm $HOME/bin/jdk1.6.0_31/bin/
./eclipse -vm $JAVA_HOME/bin
