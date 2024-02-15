CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

echo "Start grading [$1]"
git clone $1 student-submission &> cloning-results.txt

if [[ $? -ne 0 ]]
then
  echo "Failed Cloneing the Repository. Check the url and try again."
  exit 1
else
  echo 'Finished cloning'
fi

if [[ -f student-submission/ListExamples.java ]]
then
  cp student-submission/ListExamples.java ./grading-area/
  cp TestListExamples.java ./grading-area/
  echo "Files are copied to grading-area"
else
  echo "Missing file 'ListExampls.java'"
  exit 1
fi

cd grading-area

CPATH=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"

javac -cp $CPATH *.java &> compiling-results.txt

if [[ $? -ne 0 ]]
then
  echo "Code failed to Compile."
  exit 1
else
  echo "Code successfully compiled."
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples &> running-results.txt

lastline=$(cat running-results.txt | tail -n 2 | head -n 1)
status=$(echo $lastline | cut -d " " -f1)

if [[ $status == "OK" ]]
then
  tests=$(echo $lastline | cut -d "(" -f2 | cut -d " " -f1)
  suucess=$tests
else
  tests=$(echo $lastline | cut -d ":" -f2 | cut -d "," -f1)
  failures=$(echo $lastline | cut -d ":" -f3)
  success=$(($tests - $failures))
fi

echo "[Tests: $tests / Success: $success]"