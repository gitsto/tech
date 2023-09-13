#!/usr/bin/env bash

# Script par Yves Rougy, domaine public

# Script qui illustre la création des fonctions

defvar()
{ 
	A=B; 
}

defvarlocale()
(
	A=C
)

echo $A # la variable A est vide
defvar # on appelle la fonction
echo $A # la fonction a créé la variable
defvarlocale # on appelle la fonction
echo $A # la fonction a créé la variable
