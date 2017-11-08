echo "Starting deploy script"

if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    exit 1
fi

CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
if [ $CURRENT_BRANCH != "master" ]
  then
    echo "Must be on master branch"
    exit 1
fi

echo Project version $1

echo "Create a git commit to bump the version"
git commit -am "Version bump to $1"

echo "Push the version bump commit to master"
git push origin master

echo "Create a tag"
git tag v$1
git push origin v$1

echo "Create a JAR file"
sbt package

echo "Create a GitHub release"
hub release create -a target/scala-2.11/spark-bulba_2.11-2.2.0_$1.jar -m "Release v$1" v$1

