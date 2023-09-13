#!/usr/bin/env bash

#
# Script de calculatrice du cours SHL pour Orsys
# Groupe de la période du 11 au 13 Septembre 2023
# Auteur: Yves Rougy
# Fonction: Effectue une des 4 opérations +,-,/,* entre deux entiers
# Usage: Appelé sans paramètres, le script nous demande un entier, un opérateur et un entier pour faire l'opération
#        Sinon on passe l'entier, l'opérateur et l'entier pour faire l'opération
#

#set -xv

if [ "$#" -ne 3 ]  && [ "$#" -ne 0 ]
then
	echo "Mauvais nombre d'arguments !" >&2
	echo "Usage: $0 " >&2
	echo "ou" >&2
	echo "Usage: $0 nombre operateur nombre" >&2
	echo "exemple: $0 3 + 2" >&2
	exit 1
fi

if [ "$#" -eq 3 ]
then
	N1="$1"
	OP="$2"
	N2="$3"
else
	echo "Entrez un entier: "
	read N1
	echo "Entrez un opérateur: "
	read OP
	echo "Entrez un autre entier: "
	read N2
fi
expr "$N1" "$OP" "$N2"


