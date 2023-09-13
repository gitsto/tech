#!/bin/bash

while true
do
	echo je rentre dans la boucle sensible
	trap 'echo pas possible maintenant, réessaie plus tard' 2
	i=0
	while [ "$i" -lt 5 ] 
	do
		i=$(expr $i + 1)
		sleep 1
	done
	trap 2
	echo ici "c'est la boucle interruptible, non sensible"
	i=0
	while [ "$i" -lt 5 ] 
	do
		i=$(expr $i + 1)
		sleep 1
	done
	
done
