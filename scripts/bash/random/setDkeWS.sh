ls $WORKSPACE
read -p "copier coller le nom de la version dke : "
echo "version selectionnee : $REPLY"
echo "set the version and create a tmp file"
FILE=$HOME/.bashrc
sed -e s/export\ DKE_VERSION=[a-Z0-9._-]*/export\ DKE_VERSION=$REPLY/g $FILE > $FILE.tmp
ls $FILE.tmp
echo "mv $FILE.tmp $FILE"
mv $FILE.tmp $FILE
echo "source $FILE"
source $FILE
cat $FILE

echo "don't forget to open a new terminal tab to make changes take effect, CTRL+SHIFT+T, and close this one (CTRL+D)"


echo "/!\ then lauch ./patch_dke.sh in the new terminal" 

exit




