package graph;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author lzhang
 * @since 9/14/19
 */

/*
    DAG with no cycles
 */
public class TopologicalSort {
    public Stack<Integer> topoSort(Map<Integer, List<Edge>> g) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[g.size()];
        for(int currNode : g.keySet()) {
            topoSortHelper(g, stack, visited, currNode);
        }
        return stack;
    }
    private void topoSortHelper(Map<Integer, List<Edge>> g, Stack<Integer> stack, boolean[] visited, int currNode) {
        if(!visited[currNode]) {
            for(Edge edge : g.get(currNode)) {
                topoSortHelper(g, stack, visited, edge.getNeighborLabel());
            }
            visited[currNode] = true;
            stack.push(currNode);
        }
    }
}
