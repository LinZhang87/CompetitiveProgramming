package graph.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 1/27/20
 */

public class GraphBuilder {
    /*
    edges[0]: u; edges[1]: v; edges[2]: edge weight;
 */
    public static List<int[]>[] buildUndirectedGraph(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
            g[e[1]].add(new int[]{e[0], e[2]});
        }
        return g;
    }

    public static List<int[]>[] buildDirectedGraph(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
        }
        return g;
    }

    public static List<int[]>[] buildInDegreeDirectedGraph(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            g[e[1]].add(new int[]{e[0], e[2]});
        }
        return g;
    }

    public static int[][] buildUndirectedGraphAdjMatrix(int n, int[][] edges) {
        int[][] adj = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(adj[i], Integer.MAX_VALUE);
        }
        for(int[] e : edges) {
            adj[e[0]][e[1]] = e[2];
            adj[e[1]][e[0]] = e[2];
        }
        for(int i = 0; i < n; i++) {
            if(adj[i][i] == Integer.MAX_VALUE) {
                adj[i][i] = 0;
            }
        }
        return adj;
    }
}
