package de.uni.bremen.hs.io;

import de.uni.bremen.hs.model.HyperEdge;
import de.uni.bremen.hs.model.HyperGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Exporter {
    public void writeHyperGraph(final HyperGraph g, final String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("p ds " + g.size() + " " + g.countHyperEdges() + "\n");
        for (HyperEdge hyperEdge : g.getHyperEdges()) {
            List<Integer> l = new ArrayList<>(hyperEdge.getVertices());
            Collections.sort(l);
            writer.write(String.join(" ",  l.stream().map(i -> Integer.toString(i)).toList()) + "\n");
        }
        writer.close();
    }
}