package graph.directed;

import graph.common.Edge;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author lzhang
 * @since 9/14/19
 */

/*
    DAG with no cycles

    iterate over all nodes in given graph and do the following exploration if this node has not been visited.

    1. for each unvisited node, call the exploration method recursively on the head node of its outgoing edges.
    2. after step 1, mark this node as visited and push it to a global stack.

 */
public class TopologicalSort {
    public Stack<Integer> topoSort(Map<Integer, List<Edge>> g) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[g.size()];
        for(int currNode : g.keySet()) {
            topoSortHelper(g, stack, visited, currNode);
        }
        return stack;   //popping elements out of this stack gives a right topological order
    }
    private void topoSortHelper(Map<Integer, List<Edge>> g, Stack<Integer> stack, boolean[] visited, int currNode) {
        if(!visited[currNode]) {
            //if not visited, visit all its unvisited neighboring nodes first
            for(Edge edge : g.get(currNode)) {
                topoSortHelper(g, stack, visited, edge.getNeighborLabel());
            }
            //then mark the current node as visited and push it to the stack
            visited[currNode] = true;
            stack.push(currNode);
        }
    }
}
