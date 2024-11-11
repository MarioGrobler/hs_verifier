package de.uni.bremen.hs;

import de.uni.bremen.hs.io.Importer;
import de.uni.bremen.hs.model.HyperGraph;
import de.uni.bremen.hs.model.Solution;
import de.uni.bremen.hs.verifier.HSVerifier;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.err.println("Two arguments expected. Usage: hs_verifier [Path to graph] [Path to solution]");
        }

        Importer importer = new Importer();
        HyperGraph hyperGraph = importer.readHyperGraph(args[0]);
        Solution solution = importer.readSolution(args[1]);

        HSVerifier verifier = new HSVerifier();
        if(verifier.verify(hyperGraph, solution)) {
            System.out.println("Solution of size " + solution.getSolutionSize() + " is valid!");
        } else {
            System.err.println("At least one hyperedge is not hit by the given solution!");
        }
    }
}