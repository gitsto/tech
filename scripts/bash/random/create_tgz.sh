
home_dir=`pwd`

work_dir=$WORKSPACE/$DKE_VERSION/launcher/

echo "cd ${work_dir}"
cd ${work_dir}

#echo "./create_dev_release.sh $TRIGRAM"
#./create_dev_release.sh $TRIGRAM
echo "./create_dev_release.sh"
./create_dev_release.sh


option=$1

if [ $option == "vt" ]
then
	cd ${home_dir}
	./create_vt_tgz.sh
fi 

# go back to home_dir.
cd $home_dir

echo 'delete *.system_functions.properties files'
#rm /tmp/*.system_functions.properties

