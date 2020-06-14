DKE_FOLDER=$WORKSPACE/$DKE_VERSION
POM=pom.xml
# V3+
PROJECTS_V3="mdc dke_parent_pom"
PROJECTS_V2="cache_access_server dke_parent_pom"

PROJECTS=$PROJECTS_V3

if [ $# != 1 ] 
then
    echo "usage: $0 [v2 or v3]" 
    exit
fi

if [ "v2" == $1 ]
then
	PROJECTS=$PROJECTS_V2
fi

for i in $PROJECTS
do
SOURCE=$DKE_FOLDER/$i/$POM
#echo "SOURCE=$SOURCE"
TEMP=$SOURCE.temp
#echo "TEMP=$TEMP"
SED_FILE=$i.sed
#echo "SED_FILE=$SED_FILE"

tr '\n' '%' < $SOURCE > $SOURCE.tr
mv $SOURCE.tr $SOURCE
sed -f $SED_FILE $SOURCE > $TEMP
sed s/BRANCHENAME/$DKE_VERSION/g $TEMP > $TEMP.temp
mv $TEMP.temp $TEMP
#echo 
#echo ">>>>>> lines replaced in file $TEMP"
#grep -i -B 1 database_manager $TEMP
#grep -i -B 1 cache_access_server $TEMP
#grep -i limitProjectReferencesToWorkspace $TEMP
#grep -i projectNameTemplate $TEMP

tr '%' '\n' < $TEMP > $TEMP.tr
mv $TEMP.tr $TEMP
#echo "move file $TEMP to $SOURCE"
mv $TEMP $SOURCE
done
echo "Patch POMs DONE"


SOURCE=$WORKSPACE/$DKE_VERSION/launcher/create_dev_release.sh
TEMP=$SOURCE.temp
SED_FILE=create_dev_release.sed

sed "s/scp/sshpass -p \"admin\" scp/g" $SOURCE > $TEMP
mv $TEMP $SOURCE
sed "s/package_name=[a-Z0-9._-]*-XXXXX/package_name=$DKE_VERSION-$TRIGRAM/g" $SOURCE > $TEMP
mv $TEMP $SOURCE

chmod 777 $SOURCE
echo "Patch create_dev_release.sh DONE"

