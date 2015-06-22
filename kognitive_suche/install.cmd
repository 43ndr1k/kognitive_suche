#! /bin/bash
# install local jars
echo "install local jars"

# install maven dependencies
echo "install maven dependencies"
mvn validate 
mvn clean 
mvn install

pause
