# PACE Hitting Set Verifier

A small hitting set verifier for [PACE 2025](https://pacechallenge.org/2025/).
This verifier is available as a Java/Maven project. We recommend IntelliJ to build the project.
The program expects two arguments: the first argument is a path to the `.hgr`-file containing your hypergraph and the second argument is a path to the solution file. 
Alternatively, you can add your hypergraphs with solution files into the `testset` folder and run the test in the file `VerifierTest`.
The test reads all graphs in the `testset` folder, expects for each graph a solution file with the same name (and file extension `.sol` instead of `.gr`) and verifies the solution for the graph. The `testset` folder already contains a small test set.
Follow [this link](https://pacechallenge.org/2025/hs/) for more information on the input/output files.

## Manually Building on Debian/Ubuntu Linux
First install JRE 21 and Maven:
```bash
sudo apt install openjdk-21-jdk maven
```

Then navigate into the verifier directory and build using Maven:
```bash
cd Hitting\ Set\ Verifier/
mvn clean install
```

This will create a compiled JAR file `hs_verifier_${VERSION}.jar` in the directory `target`.
Execute this jar file to verify a solution:
```bash
java -jar target/hs_verifier-1.0-SNAPSHOT.jar graph.gr solution.sol
```