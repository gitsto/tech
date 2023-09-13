#!/usr/bin/env bash

#
# Script de création d'une entête pour les scripts shell
# Auteur: Yves Rougy
# Fonction: Crée une entête dans un fichier
# Usage: Appelé sans paramètres, le script nous demande les infos pour faire l'entête
#

echo "Quel est le nom du scripts ?"
read FNAME

if [ -e "$FNAME" ]
then
	echo "Le fichier existe, je m'arrête" >&2
	exit 2
fi

echo "Quel shell est à utiliser ? (bash, ksh, sh,...) "
read SHL

cat << END > "$FNAME"
#!/usr/bin/env $SHL
END

echo "Quel est l'auteur du script ?"
read AUTHOR

cat << END >> "$FNAME"
#
# Auteur: $AUTHOR
# Date: `date`
#
END

echo "Licence ? (Domaine public, BSD, GPLv2, Autre)"
read LIC

cat << END >> "$FNAME"
# Licence: $LIC
#
# Fonction du script: à remplir
# Usage du script: à remplir
END
