package graph.common;

import java.util.List;

/**
 * @author lzhang
 * @since 1/31/20
 */

/*
    Recall: APSP reduces to n invocations of SSSP.
    - Nonnegative edge lengths: O(m * n logn) via Dijkstra
    - General edge lengths: O(m * n^2) via Bellman-Ford
    - FloydWarshall: O(n^3): at least as good as n invocations of Bellman-Ford, better in dense graph as m is O(n^2).

    Johnson's algorithm reduces APSP to 1 invocation of Bellman-Ford (O(m * n)), n invocations of Dijkstra O(n * m * log n),
    the total running time is O(n * m * log n)

    This is done by applying a reweighting technique to change all edges to have non-negative weights.

    1. Form a new graph G' by adding a new vertex s and a new edge s->v with weight 0 for each v in G.

    2. Run Bellman-Ford on G' with source vertex s. If Bellman-Ford dectects a negative cost cycle in G', halt and report this.
       Otherwise, return the shortest path s->v in G' for all v, denote it as P(v) for vertex v.

    3. In G, denote the original edge length of e = (u,v) as Ce. Update all Ce to Ce' = Ce + P(u) - P(v).

    4. For each vertex v, run Dijkstra's algorithm in G with updated edge lengths, to compute the shortest path to all other vertices
       for each source vertex.

    5. For each shortest path d(u, v), update its shortest path to d(u, v) - P(u) + P(v).

    For simplicity, assuming an input of a directed graph G = (V, E), with edge lengths possibly < 0.
 */

public class Johnson {

    public static int[][] JohnsonAlgorithm(int n, int[][] edges) {
        int[][] newGraphEdge = new int[edges.length + n][3];
        for(int i = 0; i < edges.length; i++) {
            newGraphEdge[i] = edges[i];
        }
        for(int i = edges.length; i < newGraphEdge.length; i++) {
            newGraphEdge[i] = new int[]{n, i - edges.length, 0};
        }
        int[] d = BellmanFord.bellmanFordShortestPath(n + 1, newGraphEdge, n);
        if(d == null) {
            return null;
        }
        for(int[] edge : edges) {
            edge[2] += (d[edge[0]] - d[edge[1]]);
        }

        List<int[]>[] newWeightGraph = GraphBuilder.buildDirectedGraph(n, edges);
        int[][] apsp = new int[n][n];
        for(int i = 0; i < n; i++) {
            apsp[i] = Dijkstra.dijkstraShortestPaths(newWeightGraph, i);
        }
        return apsp;
    }
}
