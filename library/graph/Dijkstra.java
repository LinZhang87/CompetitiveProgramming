package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author lzhang
 * @since 7/2/19
 */

/*
Single Source Shortest Paths, efficient, can be used for processing large graphs.

Graph with non negative weight edges; There are N nodes labeled from 0 to N - 1.
Assume all graph nodes are given by their labels in ascending order

Runtime: O(V + E * log E)
 */

public class Dijkstra {
    public int[] dijkstraShortestPaths(GraphNode[] graphNodes, int startNode) {
        int[] distance = new int[graphNodes.length];
        boolean[] processed = new boolean[graphNodes.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startNode] = 0;

        PriorityQueue<int[]> minPq = new PriorityQueue<>((a1, a2) -> {return a1[0] - a2[0];});
        minPq.add(new int[]{0, startNode});
        while(!minPq.isEmpty()) {
            int[] curr = minPq.poll();
            int currNodeDistance = curr[0];
            int currNodeLabel = curr[1];
            if(processed[currNodeLabel]) {
                continue;
            }
            processed[currNodeLabel] = true;
            for(Edge edge : graphNodes[currNodeLabel].getEdges()) {
                int neighborNodeLabel = edge.getNeighborLabel();
                int weight = edge.getWeight();
                if(currNodeDistance + weight < distance[neighborNodeLabel]) {
                    distance[neighborNodeLabel] = currNodeDistance + weight;
                    minPq.add(new int[]{distance[neighborNodeLabel], neighborNodeLabel});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        GraphNode g0 = new GraphNode(0);
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);
        Edge e01 = new Edge(1, 5);
        Edge e10 = new Edge(0, 5);
        Edge e03 = new Edge(3, 9);
        Edge e30 = new Edge(0, 9);
        Edge e04 = new Edge(4, 1);
        Edge e40 = new Edge(0, 1);
        Edge e12 = new Edge(2, 2);
        Edge e21 = new Edge(1, 2);
        Edge e23 = new Edge(3, 6);
        Edge e32 = new Edge(2, 6);
        Edge e34 = new Edge(4, 2);
        Edge e43 = new Edge(3, 2);

        g0.addEdge(e01);g0.addEdge(e03);g0.addEdge(e04);
        g1.addEdge(e10);g1.addEdge(e12);
        g2.addEdge(e21);g2.addEdge(e23);
        g3.addEdge(e32);g3.addEdge(e30);g3.addEdge(e34);
        g4.addEdge(e40);g4.addEdge(e43);

        GraphNode[] nodes = new GraphNode[5];
        nodes[0] = g0; nodes[1] =g1; nodes[2] = g2; nodes[3] = g3; nodes[4] = g4;

        Dijkstra dijkstra = new Dijkstra();
        int[] distances = dijkstra.dijkstraShortestPaths(nodes, 0);
        System.out.println(Arrays.toString(distances));
    }
}
