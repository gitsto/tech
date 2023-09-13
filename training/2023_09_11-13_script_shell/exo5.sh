#!/usr/bin/env ksh

# Script ksh qui nous affiche le nombre de paramètres passés, 
# et qui nous les énumère un par un.

echo nb de params: $#

i=1
for p in "$@"
do
    echo $i eme param : $p
    i=$(expr $i + 1)
done
