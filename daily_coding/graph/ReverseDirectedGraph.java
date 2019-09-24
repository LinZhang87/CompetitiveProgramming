package graph;

import java.util.*;

/**
 * @author lzhang
 * @since 7/2/19
 */

/*
This problem was asked by Yahoo.

Write an algorithm that computes the reversal of a directed graph. For example, if a graph consists of A -> B -> C, it should become A <- B <- C.

 */
public class ReverseDirectedGraph {
    public static int[][] reverseDirectedGraph(int[][] graph) {
        Map<Integer, List<Integer>> reverseMap = new TreeMap<>();
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                if(!reverseMap.containsKey(graph[i][j])) {
                    reverseMap.put(graph[i][j], new ArrayList<>());
                }
                reverseMap.get(graph[i][j]).add(i);
            }
        }
        int[][] reverse = new int[graph.length][];
        for(int node : reverseMap.keySet()) {
            List<Integer> neighbors = reverseMap.get(node);
            Collections.sort(neighbors);
            reverse[node] = new int[neighbors.size()];
            for(int i = 0 ; i < reverse[node].length; i++) {
                reverse[node][i] = neighbors.get(i);
            }
        }
        return reverse;
    }
    public static void main(String[] args) {
        int[][] graph = new int[5][];
        graph[0] = new int[]{1,2,3,4};
        graph[1] = new int[]{2,3};
        graph[2] = new int[]{4};
        graph[3] = new int[]{0};
        graph[4] = new int[]{};

        int[][] reverse = reverseDirectedGraph(graph);
        for(int i = 0; i < reverse.length; i++) {
            System.out.println(i + " -> " + Arrays.toString(reverse[i]));
        }
    }
}
