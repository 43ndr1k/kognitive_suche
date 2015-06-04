#! /bin/bash
# install local jars
echo "install local jars"
mvn validate

# install maven dependencies
echo "install maven dependencies"
mvn clean install

pause
