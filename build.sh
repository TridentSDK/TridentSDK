BRANCH=$(git status | grep "On branch" | awk '{print $3}')

if [[ '$BRANCH' == 'bleeding-edge' ]];
then
        mvn clean install deploy --settings target/travis/settings.xml
else
        mvn clean install --settings target/travis/settings.xml
fi