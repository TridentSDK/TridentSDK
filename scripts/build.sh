echo "Currently on branch: $TRAVIS_BRANCH"

if [ "$TRAVIS_BRANCH" == "bleeding-edge" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ];
then
    echo "Compiling with deployment"
    mvn clean install deploy test jacoco:report coveralls:report --settings target/travis/settings.xml
else
    echo "Compiling without deployment"
    mvn clean install --settings target/travis/settings.xml
fi
