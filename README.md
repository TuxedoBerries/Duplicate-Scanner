# Duplicate Scaner

The small tool allows you to detect any duplicated file in a folder. You can decide to delete the duplicates leaving the first file detected or move all the diplicated files into a folder.

## Compilation
The project uses Maven, you can import the project in your favorite IDE and build from there or compile it in the terminal. Just follow this [guide](https://maven.apache.org/guides/getting-started/).

## Usage
To scan the current folder just type
```sh
$ java -jar dscanner .
```

To scan and delete all the duplicate files add the **-d** option
```sh
$ java -jar dscanner . -d
```

To move all the duplicated files into a given folder add the **-m** option followed by the destination folder (The folder will be created if not exist).
```sh
$ java -jar dscanner . -m destination_folder
```

## Example
An example of the execution is shown here
```
$ java -jar dscanner.jar .
Checking Files
File: 8db454682911c10b33d93264b3cbe22d - ./.classpath
File: 11b047c2e2aec475d915263930d43399 - ./.DS_Store
File: 870257db8cce72cfcbe61167ed1cff60 - ./.gitignore
File: d305bb3f006173075e1fc9cdbda86ca9 - ./dscanner.jar
File: d9ca19a05400a69bf171a3fd143b6a75 - ./pom.xml
File: d41d8cd98f00b204e9800998ecf8427e - ./README.md
File: a131dd10c4bd8c59119acd9ce6a1eaec - ./src/main/java/com/giantparticle/duplicatescanner/cli/CLIOptions.java
File: aaf9d9862c22cc0d3c034046e37f15c0 - ./src/main/java/com/giantparticle/duplicatescanner/cli/DuplicateScanner.java
File: 1ea781f68022a0bfe39f0d93d5f2ac2f - ./src/main/java/com/giantparticle/duplicatescanner/cli/ICLIOptions.java
File: 2867f07ba93b04954f28d3238843d49e - ./src/main/java/com/giantparticle/duplicatescanner/FileData.java
File: a1b9b6fb7b93832cba8de8550f085383 - ./src/main/java/com/giantparticle/duplicatescanner/FolderHashGenerator.java
File: d96ec3e1df1fdd9fac153b33150dba3d - ./src/main/java/com/giantparticle/duplicatescanner/HashGenerator.java
File: 254416436f7f1b005328efae0f3b22f2 - ./src/main/java/com/giantparticle/duplicatescanner/processors/BaseDuplicateProcessor.java
File: 8e4cc209ed5ca13e415dcadc282e8764 - ./src/main/java/com/giantparticle/duplicatescanner/processors/DeleteDuplicateProcessor.java
File: cb799faac92a9008368a56d5cde0a307 - ./src/main/java/com/giantparticle/duplicatescanner/processors/DisplayDuplicateProcessor.java
File: 06731a0620b554d72c607612ddb92940 - ./src/main/java/com/giantparticle/duplicatescanner/processors/DuplicateProcessorFactory.java
File: 37317ff43e4c00a310f1c5951f1d0e11 - ./src/main/java/com/giantparticle/duplicatescanner/processors/IDuplicateProcessor.java
File: b204c0a1bbd49bba7eb506a89fa6340e - ./src/main/java/com/giantparticle/duplicatescanner/processors/MoveDuplicateProcessor.java
File: 194577a7e20bdcc7afbb718f502c134c - ./target/.DS_Store
File: 4b4db48246b56b1dfdf78deea1cea9fb - ./target/classes/com/giantparticle/duplicatescanner/cli/CLIOptions.class
File: ff54c7e3ccfb85b52ea3514b518c42f6 - ./target/classes/com/giantparticle/duplicatescanner/cli/DuplicateScanner.class
File: 43a0f814905eb6445cba531b789bc4be - ./target/classes/com/giantparticle/duplicatescanner/cli/ICLIOptions.class
File: 51d3f26f7e93b482cc25d47f82cb7fd4 - ./target/classes/com/giantparticle/duplicatescanner/FileData.class
File: f8d3e742f9dd6f10fe6648ddc49ba88a - ./target/classes/com/giantparticle/duplicatescanner/FolderHashGenerator.class
File: 2f8f321f02bef5d0fb2497285a2c4f36 - ./target/classes/com/giantparticle/duplicatescanner/HashGenerator.class
File: 2f6805a55196f3dcc9c18a25eb7fe04f - ./target/classes/com/giantparticle/duplicatescanner/processors/BaseDuplicateProcessor.class
File: dd7dee571b02a3d6a3da1199fd365dbb - ./target/classes/com/giantparticle/duplicatescanner/processors/DeleteDuplicateProcessor.class
File: c7eb7d7bf8d809ff5867bf261c2dd22a - ./target/classes/com/giantparticle/duplicatescanner/processors/DisplayDuplicateProcessor.class
File: b8b9d91bd36bc63f2c1f53454c9a5173 - ./target/classes/com/giantparticle/duplicatescanner/processors/DuplicateProcessorFactory.class
File: b0ec5c3a372f92b1e44a405e66362f7a - ./target/classes/com/giantparticle/duplicatescanner/processors/IDuplicateProcessor.class
File: 3c1e55c4b399fab52e916fb9e9c4cdbd - ./target/classes/com/giantparticle/duplicatescanner/processors/MoveDuplicateProcessor.class
File: 7064c158ce1ca67a366e0873ea83b17f - ./target/DuplicateScanner-0.1-jar-with-dependencies.jar
File: d41d8cd98f00b204e9800998ecf8427e - ./target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst
File: 6963b2cf30411d0ae7a733d064e54742 - ./target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst
File: d41d8cd98f00b204e9800998ecf8427e - ./target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst

Duplicates
d41d8cd98f00b204e9800998ecf8427e
- ./README.md
- ./target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst
- ./target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst
```

### Version
0.1

### Copyright
Copyright (c) [Juan Silva](mailto:juan.silva@giantparticle.com) - [Giant Particle](http://www.giantparticle.com/) All rights reserved.
