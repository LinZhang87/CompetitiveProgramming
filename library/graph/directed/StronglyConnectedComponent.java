package graph.directed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 11/30/19
 */

/*
    Given a direct graph, find all strongly connected components in this graph.
    In a strongly connect components, you can get from any node to any other nodes

    Kosaraju's Two-Pass Algorithm:
    1. Reverse all edges and run dfs-loop on this reversed graph(each node should be traversed).
       After each node is done traversing, assign a finishing time to it in increasing order.
    2. Run dfs-loop on the original graph and process nodes in decreasing order of finishing times.
       Each dfs call on an unvisited node returns one SCC.

    The first dfs-loop computes the ordering of nodes that the second dfs-loop is going to use to discover all the SCCs
    one by one.

 */
public class StronglyConnectedComponent {
    private List<Integer>[] gRev;
    private List<Integer>[] g;
    private int sccCnt = 0;
    private int currFinishTime = 0;
    private int[] finishTimes;  //The node of label finishTimes[i] has finishing time of i
    //private int leaderNode = -1;

    public List<List<Integer>> findScc(int n, int[][] edges) {
        buildGraph(n, edges);
        finishTimes = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfsOnGRevFromNode(i, visited);
            }
        }

        List<List<Integer>> scc = new ArrayList<>();
        visited = new boolean[n];
        for(int i = n - 1; i >= 0; i--) {
            if(!visited[finishTimes[i]]) {
                List<Integer> component = new ArrayList<>();
                dfsOnGFromNode(finishTimes[i], visited, component);
                scc.add(component);
            }
        }
        return scc;
    }

    private void buildGraph(int n, int[][] edges) {
        gRev = new List[n];
        g = new List[n];

        for(int i = 0; i < n; i++) {
            gRev[i] = new ArrayList<>();
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            g[e[0]].add(e[1]);
            gRev[e[1]].add(e[0]);
        }
    }

    private void dfsOnGRevFromNode(int node, boolean[] visited) {
        visited[node] = true;
        for(int neighbor : gRev[node]) {
            if(!visited[neighbor]) {
                dfsOnGRevFromNode(neighbor, visited);
            }
        }
        finishTimes[currFinishTime] = node;
        currFinishTime++;
    }

    private void dfsOnGFromNode(int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for(int neighbor : g[node]) {
            if(!visited[neighbor]) {
                dfsOnGFromNode(neighbor, visited, component);
            }
        }
    }
}
