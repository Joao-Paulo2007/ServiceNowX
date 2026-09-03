$ErrorActionPreference = "Stop"
if (Test-Path out) { Remove-Item -Recurse -Force out }
New-Item -ItemType Directory -Path out | Out-Null
$fontes = Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $fontes
java -cp out br.edu.servicenowx.Principal
