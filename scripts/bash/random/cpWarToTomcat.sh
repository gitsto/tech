
cd $WORKSPACE/supervision/

mvn clean eclipse:eclipse install -DskipTests

#mvn clean && is &&  mvn eclipse:eclipse && rm -rf /usr/local/tomcat/webapps/supervision-webapp* && cp ./supervision-webapp/target/supervision-webapp.war /usr/local/tomcat/webapps/


echo "remove old webapps..."
rm -rf $CATALINA_HOME/webapps/supervision-webapp*

cmd="cp $WORKSPACE/supervision/supervision-webapp/target/supervision-webapp*.war $CATALINA_HOME/webapps/supervision-webapp.war"

echo "copy war to tomcat $cmd"

$cmd 

ls $CATALINA_HOME/webapps/

