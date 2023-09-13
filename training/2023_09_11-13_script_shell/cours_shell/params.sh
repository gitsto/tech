#!/usr/bin/env ksh

# Script fait pas Yves Rougy pour le cours SHL
# Ce script affiche le nombre de paramètres et les énumère un par un
# Libre d'usage

echo Le script a reçu $# paramètres
I=1

for P in "$@"
do
	echo $I ème paramètre: $P
	I=`expr $I + 1`
done

for C in `seq 1 $#`
do
	echo ${C}° param: $1
	shift
done

