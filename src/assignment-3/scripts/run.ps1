Set-Location $PSScriptRoot\..

$mainSources = Get-ChildItem -Recurse -Filter *.java src\main\java

if (Test-Path build\classes) {
    Remove-Item -Recurse -Force build\classes
}

New-Item -ItemType Directory -Force build\classes | Out-Null
javac -d build\classes $mainSources.FullName
java -cp build\classes com.example.windowsos.Main
