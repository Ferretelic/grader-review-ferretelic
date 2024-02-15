for REPO in $(cat repositories.txt)
do
  bash grade.sh $REPO
  echo ""
  echo ""
done