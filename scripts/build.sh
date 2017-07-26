#!/bin/bash

echo "Compiling without deployment"
mvn clean install --settings target/travis/settings.xml
