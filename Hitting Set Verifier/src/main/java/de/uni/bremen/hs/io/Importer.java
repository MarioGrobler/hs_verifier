package de.uni.bremen.hs.io;

import de.uni.bremen.hs.model.HyperGraph;
import de.uni.bremen.hs.model.Solution;
import de.uni.bremen.hs.model.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Importer {
    public HyperGraph readHyperGraph(final String path) throws IOException {
        HyperGraph hyperGraph = new HyperGraph();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if (line.isEmpty()) {   //empty lines are ignored; we expect every set to be non-empty
                continue;
            }
            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }

            if (line.startsWith("p")) {
                if (!strings[1].equals("hs")) {
                    throw new IOException("Problem specifier is not hs in line " + i);
                }
                continue;
            }

            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) { //empty line
                continue;
            }

            Set<Integer> hyperedge = new HashSet<>();
            Arrays.stream(strings).forEach(s -> {
                int v = Integer.parseInt(s);
                if(!hyperGraph.vertexExists(v)) {
                    hyperGraph.addVertex(new Vertex(v));
                }
                hyperedge.add(Integer.parseInt(s));
            });

            hyperGraph.addHyperEdge(hyperedge);
        }
        return hyperGraph;
    }

    public Solution readSolution(final String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        int i = 0;
        int solutionSize = -1;

        //find first number
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if (line.isEmpty()) {
                continue;
            }

            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }
            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) {
                continue;
            }
            if (strings.length == 1) {
                solutionSize = Integer.parseInt(strings[0]);
                break;
            }
            throw new IOException("Line " + i + " does not contain a number");
        }

        Solution solution = new Solution(solutionSize);
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if (line.isEmpty()) {
                continue;
            }

            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }
            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) {
                continue;
            }
            if (strings.length == 1) {
                solution.addVertex(Integer.parseInt(strings[0]));
                continue;
            }
            throw new IOException("Line " + i + " does not contain a number");
        }

        return solution;
    }
}