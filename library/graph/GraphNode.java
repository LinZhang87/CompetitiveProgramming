package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 9/16/19
 */

public class GraphNode {
    private int label;
    private List<Edge> edges;

    public GraphNode(int label) {
        this.label = label;
        this.edges = new ArrayList<>();
    }
    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }
}
