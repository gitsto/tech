#!/bin/bash

home_folder=/mnt/projet_dke/DKE_No_Functional_test/Livraisons/02_Cahier_de_test/Performances
call_types="01_Entrants 02_Sortants 03_Internes 04_Mixtes"
rate_type="01_Pics_de_charge 02_Stationnaire 03_Endurance"

mkdir -p $home_folder
echo "$home_folder"
echo "loop in the call_types=$call_types"
for ct in $call_types
do 
  echo "loop in the rate_types=$rate_type"
  for rt in $rate_type
  do
    mkdir -p $home_folder/$ct/$rt
    mkdir -p $home_folder/$ct/$rt
    mkdir -p $home_folder/$ct/$rt
  done
done

ls -R $home_folder

echo "done."
exit


