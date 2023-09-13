#!/usr/bin/env bash

# Script par Yves Rougy, domaine public

# Exemple du for

for I in $*
do
	echo le paramètre est: $I
done
echo '$1 a pour valeur: ' $1
for I in "$@"
do
	echo le paramètre est: $I
done
