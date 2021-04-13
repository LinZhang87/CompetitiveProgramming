package tree.common;

import tree.common.TreeNode;

import java.util.*;

/**
 * @author lzhang
 * @since 7/25/20
 */

/*
    Given a tree with possible duplicated tree node values, convert it to to an undirected graph with unqiue labels,
    return the graph connections represented by labels
 */
public class TreeToGraphConversion {
    private int label = 0;

    private void dfsRelabel(TreeNode node) {
        if(node != null) {
            for(TreeNode child : node.children) {
                dfsRelabel(child);
            }
            node.val = label;
            label++;
        }
    }

    //DFS to convert tree to undirected graph
    public List<Integer>[] dfsToUndirectedGraph(TreeNode root) {
        //first re-label all tree nodes such that each node's value is unique
        dfsRelabel(root);

        List<Integer>[] g = new List[label];
        for(int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }

        dfsHelper(g, root);
        return g;
    }

    private void dfsHelper(List<Integer>[] g, TreeNode node) {
        for(TreeNode child : node.children) {
            dfsHelper(g, child);
            g[node.val].add(child.val);
            g[child.val].add(node.val);
        }
    }

    //BFS to covert to tree to undirected graph
    public List<Integer>[] bfsToUndirectedGraph(TreeNode root) {
        List<Integer>[] g = new List[label];
        for(int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(q.size() > 0) {
            TreeNode curr = q.poll();
            for(TreeNode child : curr.children) {
                g[curr.val].add(child.val);
                g[child.val].add(curr.val);
                q.add(child);
            }
        }

        return g;
    }
}
