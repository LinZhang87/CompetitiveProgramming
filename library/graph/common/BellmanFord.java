package graph.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 1/27/20
 */

/*
    BellmanFord, Dynamic Programming, O(n * m) runtime, n is the number of vertices, m is the number of edges

    Single-Source Shortest Path Problem with possibly negative weight edges in a directed graph.

    Goal: Either
    (A) For all destinations v in V, compute the length of a shortest s->v path
    OR
    (B) Output a negative cycle

    Negative cycle detection, the normal bellman ford algorithm only detects such cycles that are reachable from the source vertex s.
    To detect negative cycle regardless of reachable from s or not, we can add a dummy vertex and add an edge of weight 0 from it to all other nodes.
    Then perform bellman ford algorithm using this dummy vertex as the source.


 */
public class BellmanFord {

    /*
        g is a graph that stores incoming nodes label and edge weights. In order to update a node's shortest path to s,
        we need all the up-stream nodes info.
     */
    public static int[] bellmanFordShortestPath(int n, int[][] edges, int source) {
        List<int[]>[] g = GraphBuilder.buildInDegreeDirectedGraph(5, edges);
        int[][] dp = new int[n + 1][n];

        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][source] = 0;

        boolean update = false;
        int i = 1;
        for(; i <= n; i++) {
            update = false;
            for(int j = 0; j < n; j++) {
                //inherit the previous iteration
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                for(int[] e : g[j]) {
                    //if its neighboring node has been reached from source node, try to use it to update
                    if(dp[i - 1][e[0]] < Integer.MAX_VALUE) {
                        int dist = dp[i - 1][e[0]] + e[1];
                        if(dist < dp[i][j]) {
                            dp[i][j] = dist;
                            update = true;
                        }
                    }
                }
            }
            if(!update) {
                break;
            }
        }

        // If the extra iteration of n edges makes some updates, there is a negative cycle that is reachable from source
        if(update && i == n + 1) {
            return null;
        }
        return dp[i];
    }

    public static void main(String[ ] args) {
        int[][] edges = {{0,1,2},{0,2,3},{0,3,7},{1,3,3},{1,4,5},{2,3,-2},{3,4,2}};
        BellmanFord bellmanFord = new BellmanFord();
        int[] dist = bellmanFord.bellmanFordShortestPath(5, edges, 0);
        System.out.println();
    }
}
