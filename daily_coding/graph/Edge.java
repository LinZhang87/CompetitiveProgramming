package graph;

/**
 * @author lzhang
 * @since 7/18/19
 */

public class Edge {
    int v1;
    int v2;
    int weight;
    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
    public int getV1() {
        return this.v1;
    }
    public int getV2() {
        return this.v2;
    }
    public int getWeight() {
        return this.weight;
    }
}
