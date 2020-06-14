DKE_FOLDER=$WORKSPACE/$DKE_VERSION
SOURCE=$WORKSPACE/$DKE_VERSION/launcher/create_dev_release.sh
TEMP=$SOURCE.temp
sed "s/package_name=[a-Z0-9._-]*-XXXXX/package_name=$DKE_VERSION-$TRIGRAM/g" $SOURCE > $TEMP
mv $TEMP $SOURCE

chmod 777 $SOURCE
echo "Patch create_dev_release.sh DONE"

