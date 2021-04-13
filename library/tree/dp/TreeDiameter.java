package tree.dp;

import tree.common.TreeNode;

import java.util.Arrays;

/*
    Two O(N) algorithms:
    1. bfs/dfs from an arbitrary node to find a node X that is furthest away. Then bfs/dfs from X to its furthest node, this
    distance is the tree diameter
    2. dynamic programming: each path in a tree has a highest node in its path. Thus, for each node as the highest point of a path,
    we can compute (1) its max length to a leaf node in its subtree (2) the max length of a path using this node as the highest point
    The tree diameter is the max of (2) over all nodes.
 */
public class TreeDiameter {
    private int diameter = 0;
    public int computeTreeDiameterUsingDp(TreeNode root) {
        dfs(root);
        return diameter;
    }
    private int[] dfs(TreeNode curr) {
        //dp[0]: max len to leaf node in subtree; dp[1]: max len with curr node as the highest point
        int[] dp = new int[2];
        Arrays.fill(dp, -1);
        if(curr != null) {
            int first = -1, second = -1;
            for(TreeNode child : curr.children) {
                int[] childDp = dfs(child);
                if(childDp[0] > first) {
                    second = first;
                    first = childDp[0];
                }
                else if(childDp[0] > second) {
                    second = childDp[0];
                }
            }
            dp[0] = first + 1;
            dp[1] = first + second + 2;
            diameter = Math.max(diameter, dp[1]);
        }
        return dp;
    }
}
