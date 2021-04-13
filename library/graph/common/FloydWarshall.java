package graph.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 1/28/20
 */

/*
    All-Pairs Shortest Paths, O(n^3) runtime, n is the number of vertices.

    Works even with graphs with negative edge lengths.

    At least as good as n Bellman-fords, better in dense graphs;

    In graphs with nonnegative edge costs, competitive with n dijkstra's in dense graphs.

    In sparse graphs with nonnegative edge costs, n dijkstra's takes O(n * m * log n), this should be used if applicable.

    Important special case: Transitive closure of a binary relation.(all-pairs reachability)


    For simplicity, assume undirected graph
 */
public class FloydWarshall {
    public static int[][] floydWarshallAPSP(int n, int[][] edges) {
        int[][] adj = GraphBuilder.buildUndirectedGraphAdjMatrix(n, edges);
        int[][] dist = new int[n][n];
        int[][] maxInternalNode = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = adj[i][j];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(maxInternalNode, -1);
        }

        //k is the intermediate node, must be in the outer loop!
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(dist[i][k] < Integer.MAX_VALUE && dist[k][j] < Integer.MAX_VALUE) {
                        //dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        if(dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            maxInternalNode[i][j] = k;
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static boolean checkNegativeCycle(int[][] dist) {
        for(int i = 0; i < dist.length; i++) {
            if(dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }

    /*
        Assuming there is negative cycle from source to destination
     */
    public static List<Integer> constructShortestPath(int[][] maxInternalNode, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        constructShortestPathHelper(maxInternalNode, source, destination, path);
        return path;
    }

    private static void constructShortestPathHelper(int[][] maxInternalNode, int u, int v, List<Integer> path) {
        if(maxInternalNode[u][v] < 0) {
            return;
        }
        path.add(maxInternalNode[u][v]);
        constructShortestPathHelper(maxInternalNode, u, maxInternalNode[u][v], path);
        constructShortestPathHelper(maxInternalNode, maxInternalNode[u][v], v, path);
    }
}
