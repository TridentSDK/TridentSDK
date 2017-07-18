#!/bin/bash

echo "Currently on branch: $TRAVIS_BRANCH"

if [ "$TRAVIS_BRANCH" == "revamp" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ];
then
    gradlew jacocoTestReport uploadArchives

    # Publish coverage report
    bash <(curl -s https://codecov.io/bash)
fi