package graph;

/**
 * @author lzhang
 * @since 8/8/19
 */

/*
The transitive closure of a graph is a measure of which vertices are reachable from other vertices.
It can be represented as a matrix M, where M[i][j] == 1 if there is a path between vertices i and j, and otherwise 0.

For example, suppose we are given the following graph in adjacency list form:

graph = [
    [0, 1, 3],
    [1, 2],
    [2],
    [3]
]
The transitive closure of this graph would be:

[1, 1, 1, 1]
[0, 1, 1, 0]
[0, 0, 1, 0]
[0, 0, 0, 1]
Given a graph, find its transitive closure.
 */

/*
Assume there are n nodes labelled from 0 to n - 1. If a node has no outgoing edges, it will have an empty list.

[1, 1, 0, 1]
[0, 1, 1, 0]
[0, 0, 1, 0]
[0, 0, 0, 1]
 */
import java.util.*;

public class TransitiveClosure {
    private static int[][] matrix;
    public static int[][] transitiveClosure(Map<Integer, List<Integer>> graph) {
        int n = graph.size();
        matrix = new int[n][n];

        for(int i = 0; i < n; i++) {
            dfs(graph, i, i);
        }
        return matrix;
    }
    private static void dfs(Map<Integer, List<Integer>> graph, int s, int v) {
        matrix[s][v] = 1;
        for(int neighbor : graph.get(v)) {
            if(matrix[s][neighbor] == 0) {
                dfs(graph, s, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Integer[] a0 = {0,1,3};
        Integer[] a1 = {1,2};
        Integer[] a2 = {2};
        Integer[] a3 = {3};
        graph.put(0, Arrays.asList(a0));
        graph.put(1, Arrays.asList(a1));
        graph.put(2, Arrays.asList(a2));
        graph.put(3, Arrays.asList(a3));

        int[][] m = transitiveClosure(graph);
        for(int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
    }
}
