package graph.undirected;

/**
 * @author lzhang
 * @since 11/18/19
 */

/*
    Dfs to check if a graph is bipartite.
    a graph is bipartite if we can split it's set of nodes into two independent subsets A and B
    such that every edge in the graph has one node in A and another node in B.
 */
public class BipartiteGraph {
    private int RED = 1, BLUE = -1, UNCOLORED = 0;
    private int[] color;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        for(int i = 0; i < n; i++) {
            if(color[i] == UNCOLORED) {
                boolean flag = dfs(graph, i, RED);
                if(!flag) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean dfs(int[][] g, int v, int c) {
        color[v] = c;
        for(int w : g[v]) {
            if(color[w] == c) {
                return false;
            }
            else if(color[w] == UNCOLORED) {
                boolean flag = dfs(g, w, -1 * c);
                if(!flag) {
                    return false;
                }
            }
        }
        return true;
    }
}
