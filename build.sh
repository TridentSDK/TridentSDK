BRANCH=$(git branch | grep "\*" | awk '{print $2}')
git branch
echo "Currently on branch:  $BRANCH"

if [ "$BRANCH" == "bleeding-edge" ];
then
    echo "Compiling with deployment"
    mvn clean install deploy --settings target/travis/settings.xml
else
    echo "Compiling without deployment"
    mvn clean install --settings target/travis/settings.xml
fi