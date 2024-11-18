package de.uni.bremen.hs.model;

import java.util.*;
import java.util.stream.Collectors;

public class HyperGraph {
    protected Map<Integer, Vertex> vertexMap;
    protected Set<HyperEdge> hyperEdgeSet;
    protected Map<Integer, Set<HyperEdge>> vertexToHyperEdgeMap;

    protected int nextVertexId;

    public int getNextVertexId() {
        return nextVertexId;
    }

    public HyperGraph() {
        this.vertexMap = new HashMap<>();
        this.hyperEdgeSet = new HashSet<>();
        this.vertexToHyperEdgeMap = new HashMap<>();
    }

    public HyperGraph(final int n) {
        this.vertexMap = new HashMap<>(n);
        this.hyperEdgeSet = new HashSet<>();
        this.vertexToHyperEdgeMap = new HashMap<>(n);

        for (int i = 1; i < n + 1; i++) {
            addVertex(new Vertex(i));
        }

    }

    public Vertex addVertex(final Vertex vertex) {
        if (vertexMap.containsKey(vertex.getId())) {
            throw new IllegalArgumentException("Graph contains Vertex with same id");
        }
        nextVertexId = vertex.getId() > nextVertexId ? vertex.getId() + 1 : nextVertexId + 1;
        vertexMap.put(vertex.getId(), vertex);
        vertexToHyperEdgeMap.put(vertex.getId(), new HashSet<>());
        return vertex;
    }

    public HyperEdge addHyperEdge(final int... ids) {
        return addHyperEdge(Arrays.stream(ids).boxed().collect(Collectors.toSet()));
    }

    public HyperEdge addHyperEdge(final Collection<Integer> ids) {
        Set<Integer> edge = new HashSet<>(ids);
        if(!vertexMap.keySet().containsAll(edge)) {
            throw new IllegalArgumentException("Tried to add a hyperedge containing a vertex not in the graph");
        }
        HyperEdge hyperEdge = new HyperEdge(edge);
        hyperEdgeSet.add(hyperEdge);
        edge.forEach(i -> {
            vertexToHyperEdgeMap.get(i).add(hyperEdge);
        });
        return hyperEdge;
    }

    public int degreeOf(final int id) {
        return vertexToHyperEdgeMap.get(id).size();
    }

    public Set<HyperEdge> getIncidentHyperEdges(final int id) {
        return Collections.unmodifiableSet(vertexToHyperEdgeMap.get(id));
    }

    public boolean vertexExists(final int id) {
        return vertexMap.containsKey(id);
    }

    public boolean removeVertex(final int id) {
        if(vertexMap.containsKey(id)) {
            vertexToHyperEdgeMap.get(id).forEach(hyperEdge -> hyperEdge.getVertices().remove(id));
            vertexToHyperEdgeMap.remove(id);
            hyperEdgeSet.removeIf(HyperEdge::isEmpty);
            return vertexMap.remove(id) != null;
        }
        return false;
    }

    public int size() {
        return vertexMap.size();
    }

    public int countHyperEdges() {
        return hyperEdgeSet.size();
    }

    public Collection<HyperEdge> getHyperEdges() {
        return hyperEdgeSet;
    }
}
