package tree.dp;

import tree.common.TreeNode;

/*
    calculate for every tree node x a value maxLength(x): the maximum length of a path that
    begins at node x.

    This can be seen as a generalization of the tree diameter problem, because the largest of
    those lengths equals the diameter of the tree.

    O(N) dynamic programming:
 */
public class AllLongestPaths {
    /*
        n nodes from 0 to n - 1, node 0 is the root node
        maxPath[i][0]: max length of a path from node i to a leaf and which child node this path goes through
        maxPath[i][1]: second max length of a path from node i to another leaf in another direction
        maxDown[i]: max length of path from node i to a leaf in i's subtree
     */
    private int[][][] maxPath;
    private int[] maxDown;
    private int[] maxDownChild;
    private int[] secondMaxDown;
    private int[] secondMaxDownChild;
    private int[] parent;
    public int[] computeAllLongestPaths(int n, TreeNode root) {
        maxPath = new int[n][2][2];
        maxDown = new int[n];
        maxDownChild = new int[n];
        secondMaxDown = new int[n];
        secondMaxDownChild = new int[n];
        parent = new int[n];
        dfs1(root, -1);
        dfs2(root);
        int[] longestPaths = new int[n];
        for(int i = 0; i < n; i++) {
            longestPaths[i] = maxPath[i][0][0];
        }
        return longestPaths;
    }

    //compute maxDown and secondMaxDown
    private int dfs1(TreeNode currNode, int p) {
        parent[currNode.val] = p;
        if(currNode == null) {
            return 0;
        }
        int maxPath = -1, secondMaxPath = -1;
        int maxPathChild = -1, secondMaxPathChild = -1;
        for(TreeNode child : currNode.children) {
            int childPath = dfs1(child, currNode.val);
            if(childPath > maxPath) {
                maxPath = childPath;
                maxPathChild = child.val;
            }
            else if(childPath > secondMaxPath) {
                secondMaxPath = childPath;
                secondMaxPathChild = child.val;
            }
        }
        maxDown[currNode.val] = maxPath + 1;
        maxDownChild[currNode.val] = maxPathChild;
        secondMaxDown[currNode.val] = secondMaxPath + 1;
        secondMaxDownChild[currNode.val] = secondMaxPathChild;
        return maxDown[currNode.val];
    }

    //compute maxPath using max / second max down and parent info
    private void dfs2(TreeNode currNode) {
        if(currNode != null) {
            //root node
            if(parent[currNode.val] < 0) {
                maxPath[currNode.val][0][0] = maxDown[currNode.val];
                maxPath[currNode.val][0][1] = maxDownChild[currNode.val];
                maxPath[currNode.val][1][0] = secondMaxDown[currNode.val];
                maxPath[currNode.val][1][1] = secondMaxDownChild[currNode.val];
            }
            else {
                //parent node's max path does not go through currNode
                int upWardMaxPath = 0;
                if(maxPath[parent[currNode.val]][0][1] != currNode.val) {
                    upWardMaxPath = maxPath[parent[currNode.val]][0][0] + 1;
                }
                //parent node's max path goes through currNode
                else {
                    upWardMaxPath = maxPath[parent[currNode.val]][1][0] + 1;
                }
                if(maxDown[currNode.val] > upWardMaxPath) {
                    maxPath[currNode.val][0][0] = maxDown[currNode.val];
                    maxPath[currNode.val][0][1] = maxDownChild[currNode.val];
                    maxPath[currNode.val][1][0] = upWardMaxPath;
                    maxPath[currNode.val][1][1] = parent[currNode.val];
                }
                else {
                    maxPath[currNode.val][0][0] = upWardMaxPath;
                    maxPath[currNode.val][0][1] = parent[currNode.val];
                    maxPath[currNode.val][1][0] = maxDown[currNode.val];
                    maxPath[currNode.val][1][1] = maxDownChild[currNode.val];
                }
            }
            for(TreeNode child : currNode.children) {
                dfs2(child);
            }
        }
    }
}
