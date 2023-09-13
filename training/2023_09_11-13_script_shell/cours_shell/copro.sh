#!/usr/bin/env ksh

./calc.sh|&

read -p entree
print -p 3
read -p entree
print -p +
read -p entree
print -p 5
read -p entree
echo la réponse est $entree
