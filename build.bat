@echo off
echo Compiling...
javac -cp "lib/*" *.java
if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)
echo Running tests...
java -cp ".;lib/*" org.junit.runner.JUnitCore TrainTest 