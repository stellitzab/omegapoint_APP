#! /bin/bash

#Compile code
echo ">> Compiling source code..."
javac Validator.java PersonalNumberCheck.java

# Run program
echo ">> Running program..."
java Validator
