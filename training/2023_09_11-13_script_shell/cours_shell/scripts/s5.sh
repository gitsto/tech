#!/usr/bin/env bash

# Script par Yves Rougy, domaine public

# Script qui illustre les paramètres des fonctions

defvar()
{ 
	echo "Le premier paramètre du script est: " $1
	echo "Le paramètre 0 du script est: " $0
	echo "Le second paramètre du script est: " $2
}

defvar $*

