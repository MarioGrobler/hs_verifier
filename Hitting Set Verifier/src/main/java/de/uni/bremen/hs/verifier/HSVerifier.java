package de.uni.bremen.hs.verifier;

import de.uni.bremen.hs.model.HyperEdge;
import de.uni.bremen.hs.model.HyperGraph;
import de.uni.bremen.hs.model.Solution;

import java.util.HashSet;
import java.util.Set;

public class HSVerifier {
    public boolean verify(final HyperGraph hyperGraph, final Solution solution) {
        if(!solution.isConsistent()) {
            throw new VerificationException("Solution is not consistent. Specified solution size: " +
                    solution.getSolutionSize() + ", given solution size: " + solution.getSolution().size());
        }

        Set<HyperEdge> hit = new HashSet<>();

        for(int vertex : solution.getSolution()) {
            if(!hyperGraph.vertexExists(vertex)) {
                throw new VerificationException("Vertex " + vertex + " does not exist in the hypergraph");
            }
            hit.addAll(hyperGraph.getIncidentHyperEdges(vertex));
        }
        return hyperGraph.countHyperEdges() == hit.size();
    }
}
