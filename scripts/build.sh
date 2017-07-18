echo "Currently on branch: $TRAVIS_BRANCH"

if [ "$TRAVIS_BRANCH" == "bleeding-edge" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ];
then
    echo "Compiling with deployment"
    gradlew clean build jar jacocoTestReport uploadArchives
else
    echo "Compiling without deployment"
    gradlew clean build jar jacocoTestReport
fi
