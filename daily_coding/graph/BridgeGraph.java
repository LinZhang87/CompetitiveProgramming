package graph;

import java.util.*;

/**
 * @author lzhang
 * @since 8/15/19
 */

/*
This problem was asked by Mozilla.

A bridge in a connected (undirected) graph is an edge that,
if removed, causes the graph to become disconnected. Find all the bridges in a graph.
 */

/*
Assume there are N nodes labelled from 0 to N - 1.
 */
public class BridgeGraph {
    private static int[] appeared;
    private static int[] reach;
    private static List<int[]> bridges = new ArrayList<>();
    public static List<int[]> getBridges(Map<Integer, Set<Integer>> graph) {
        appeared = new int[graph.size()];
        reach = new int[graph.size()];
        Arrays.fill(appeared, -1);
        Arrays.fill(reach, -1);
        dfs(graph, 0, 0, 0);
        return bridges;
    }
    private static void dfs(Map<Integer, Set<Integer>> graph, int u, int v, int depth) {
        appeared[v] = depth;
        reach[v] = depth;

        for(int neighbor : graph.get(v)) {
            if(appeared[neighbor] == -1) {
                dfs(graph, v, neighbor, depth + 1);
                if(reach[neighbor] == appeared[neighbor]) {
                    bridges.add(new int[]{v, neighbor});
                }
                reach[v] = Math.min(reach[v], reach[neighbor]);
            }
            else if(neighbor != u) {
                reach[v] = Math.min(reach[v], appeared[neighbor]);
            }
        }
    }
    public static void main(String[] args) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> edges0 = new HashSet<>();
        edges0.add(1); edges0.add(2); edges0.add(3);
        graph.put(0, edges0);

        Set<Integer> edges1 = new HashSet<>();
        edges1.add(0); edges1.add(5);
        graph.put(1, edges1);

        Set<Integer> edges2 = new HashSet<>();
        edges2.add(0); edges2.add(3);
        graph.put(2, edges2);

        Set<Integer> edges3 = new HashSet<>();
        edges3.add(0); edges3.add(2); edges3.add(4);
        graph.put(3, edges3);

        Set<Integer> edges4 = new HashSet<>();
        edges4.add(3);
        graph.put(4, edges4);

        Set<Integer> edges5 = new HashSet<>();
        edges5.add(1);
        graph.put(5, edges5);

        List<int[]> bridges = getBridges(graph);
        for(int i = 0; i < bridges.size(); i++) {
            System.out.println(Arrays.toString(bridges.get(i)));
        }
    }
}
