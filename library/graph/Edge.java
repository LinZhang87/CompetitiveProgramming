package graph;

/**
 * @author lzhang
 * @since 9/16/19
 */

public class Edge {
    private int neighborLabel;
    private int weight;

    public Edge(int neighborLabel, int weight) {
        this.neighborLabel = neighborLabel;
        this.weight = weight;
    }

    public int getNeighborLabel() {
        return neighborLabel;
    }

    public void setNeighborLabel(int neighborLabel) {
        this.neighborLabel = neighborLabel;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
