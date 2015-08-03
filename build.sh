BRANCH=$(git symbolic-ref HEAD --short)

if [[ '$BRANCH' == 'bleeding-edge' ]];
then
        mvn clean install deploy --settings target/travis/settings.xml
else
        mvn clean install --settings target/travis/settings.xml
fi