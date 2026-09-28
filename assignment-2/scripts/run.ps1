$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $PSScriptRoot
$sourceRoot = Join-Path $projectRoot "src"
$buildRoot = Join-Path $projectRoot "build/classes"

$resolvedProjectRoot = [System.IO.Path]::GetFullPath($projectRoot)
$resolvedBuildRoot = [System.IO.Path]::GetFullPath($buildRoot)
if (-not $resolvedBuildRoot.StartsWith($resolvedProjectRoot, [System.StringComparison]::OrdinalIgnoreCase)) {
    throw "Refusing to clean build directory outside the project root: $resolvedBuildRoot"
}

if (Test-Path $buildRoot) {
    Remove-Item -Recurse -Force $buildRoot
}

New-Item -ItemType Directory -Force $buildRoot | Out-Null

$sources = Get-ChildItem -Path $sourceRoot -Filter *.java -Recurse | ForEach-Object { $_.FullName }
javac -d $buildRoot $sources
java -cp $buildRoot Main
