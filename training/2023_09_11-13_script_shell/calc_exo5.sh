#!/usr/bin/env ksh

# Nous allons créer une calculatrice. Faites un script qui:
# Appelé sans paramètre, nous demande:
# un nombre entier,
# un opérateur (+,-,*,/),
# un nombre entier,
# et nous retourne l’opération décrite.
# Appelé avec 3 paramètres effectue l’opération décrite par les paramètres.
# Appelé avec un mauvais nombre de paramètres, nous affiche un message
# d’erreur explicite.

usage() {
    echo "Utilisation:"
    echo "$0 un_nombre_entier un_opérateur_(+,-,*,/) un_nombre_entier"
    exit 0
}


calc_sans_param() {
    # echo Calculatrice $ligne_separation
    echo -n saisir votre premier nombre entier:
    read a
    echo "saisir votre operateur (+,-,*,/)"
    read op
    echo -n saisir votre second nombre entier:
    read b
    echo RESULTAT $ligne_separation
    echo $(( "$a" "$op" "$b" ))
    # return $(( $a $op $b ))
    exit 0
}

calc_avec_3_params() {
    # echo Calculatrice 3 params $ligne_separation
    echo RESULTAT $ligne_separation
    echo calc_avec_3_params $1 $2 $3
    if [ "$2" == "\*" ]; then
        # echo multiplication
        expr $1 "*" $3
    else
        expr $1 $2 $3
    fi
    exit 0
}

ligne_separation=$(printf '=%.0s' {1..50})

# echo nb de param: $#

main() {
    echo les parametres du script: $@
    echo les parametres du script: $*
    for param in $*
    do
        echo $param
    done

    [ $# -eq 0 ] && calc_sans_param
    [ $# -ge 3 ] && calc_avec_3_params "$1" "$2" "$3" "$stuff"
    usage
}

main "$@"
