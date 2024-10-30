package de.uni.bremen.hs.model;

import java.util.Objects;
import java.util.Set;


public class HyperEdge {
    private final Set<Integer> vertices;

    protected HyperEdge(Set<Integer> vertices) {
        this.vertices = vertices;
    }

    protected Set<Integer> getVertices() {
        return vertices;
    }

    protected boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HyperEdge hyperEdge = (HyperEdge) o;
        return Objects.equals(vertices, hyperEdge.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vertices);
    }

    @Override
    public String toString() {
        return "HyperEdge{" +
                "vertices=" + vertices +
                '}';
    }
}
