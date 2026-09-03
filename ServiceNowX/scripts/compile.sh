#!/usr/bin/env bash
set -e
rm -rf out
mkdir -p out
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out br.edu.servicenowx.Principal
