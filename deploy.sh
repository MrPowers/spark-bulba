echo "Starting deploy script"

if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    exit 1
fi

echo Project version $1

echo "Create a git commit to bump the version"
git commit -am "Version bump to $1"

echo "Push the version bump commit to master"
git push origin master


