package graph.undirected;

import java.util.List;

/**
 * @author lzhang
 * @since 11/21/19
 */

    /*
        Offline algorithm using DFS. The input graph will not change
        Preprocess undirected graph to answer queries of the form is v connected to w? in constant time

        Assume there are n vertices labelled from 0 to n - 1. If there are c different connected components,
        their identifiers will be from 0 to c - 1.


        Online algorithm should use Union Find to support dynamic edge addition efficiently
        
     */
public class ConnectedComponent {
    private boolean[] visited;
    private int count;
    private int[] id;

    //find connected components in graph
    public ConnectedComponent(List<Integer>[] graph) {
        int n = graph.length;
        id = new int[n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(List<Integer>[] graph, int v) {
        visited[v] = true;
        id[v] = count;
        for(int w : graph[v]) {
            if(!visited[w]) {
                dfs(graph, w);
            }
        }
    }

    //are v and w connected?
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    //number of connected components
    public int count() {
        return count;
    }

    //component identifier for v
    public int id(int v) {
        return id[v];
    }
}
