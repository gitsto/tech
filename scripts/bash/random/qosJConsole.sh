lot=$WORKSPACE/$DKE_VERSION

echo "\nDescription : "
echo "this script will connect the jconsole to a host" 
echo "usage: $0 [port]" 
echo "example: $0 50001" 
echo "LOT ${lot}"
masterCab=$1
port=$2

if [ $# == 2 ]
then
	echo "put the port = ${port} in the command"
else
	port=50001
	echo "using default port for sas ${port}, change it to 50000 for a turret or 501XX for a virtual turret (vt-core)"
fi

echo "check the JARS !!"

#ls ${lot}/supervision_interfaces/target/*.jar
#ls ${lot}/shared_objects/target/*.jar
ls ${lot}/supervision_apis_objects/target/*.jar
ls ${lot}/apis_objects/target/*.jar

jar_version=3.0.4.0.27-SNAPSHOT
echo "script jar version = ${jar_version}"


jconsole ${masterCab}:${port} -J-Djava.class.path="$JAVA_HOME/lib/jconsole.jar:$JAVA_HOME/lib/tools.jar:${lot}/apis_objects/target/data_object-${jar_version}.jar:${lot}/dto_supervision_objects/target/supervision_apis_objects-${jar_version}.jar"

