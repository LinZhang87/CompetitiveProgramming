package dp;

import java.util.*;

/**
 * @author lzhang
 * @since 2/6/19
 */

public class MaxGraphPath {
    private final int UNVISITED = 0;
    private final int VISITING = 1;
    private final int VISITED = 2;
    public int maxGraphPath(String s, int[][] edges) {
        Map<Integer, List<Integer>> graph = constructGraph(s, edges);
        int[] states = new int[s.length()];
        Arrays.fill(states, UNVISITED);
        int[][] maxPaths = new int[s.length()][26];

        for(int node = 0; node < s.length(); node++) {
            if(states[node] == UNVISITED) {
                if(dfs(s, graph, states, maxPaths, node)) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        int maxPathValue = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < 26; j++) {
                maxPathValue = Math.max(maxPaths[i][j], maxPathValue);
            }
        }
        return maxPathValue;
    }
    private Map<Integer, List<Integer>> constructGraph(String s, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        return graph;
    }
    private boolean dfs(String s, Map<Integer, List<Integer>> graph, int[] states, int[][] maxPaths, int node) {
        if(states[node] == VISITED) {
            return false;
        }
        else if(states[node] == VISITING) {
            return true;
        }
        states[node] = VISITING;
        for(int neighbor : graph.get(node)) {
            dfs(s, graph, states, maxPaths, neighbor);
        }
        for(int neighbor : graph.get(node)) {
            for(int letter = 0; letter < 26; letter++) {
                maxPaths[node][letter] = Math.max(maxPaths[node][letter], maxPaths[neighbor][letter]);
            }
        }
        maxPaths[node][s.charAt(node) - 'A']++;
        states[node] = VISITED;
        return false;
    }
}
