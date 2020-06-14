#!/bin/bash

#Support d’un troisième paramètre, optionnel, qui est repassé à create_dev_release.sh
#Ce script peut maintenant être utilisé comme suit
#./build_dev_release.sh -f V4.2 MLe
#Ou
#./build_dev_release.sh -f V3.2 MLe
#Pour générer les deux paquetages dke et dkeVT de nom Dev_TRUNK_MLe(phase mvn install (...) incluse)
#Sans option ce script pose les 3 questions: version de configuration ? parametres de create_dev_release ? et confirmation de génération du paquetage dkeVT ?
#Avec la seule option -f le script pose uniquement la première question: version de configuration ?


home_dir=`pwd`
work_dir=$WORKSPACE/$DKE_VERSION/launcher/
echo "cd ${work_dir}"
cd ${work_dir}

./build_dev_release.sh -f V4.4 SSc

