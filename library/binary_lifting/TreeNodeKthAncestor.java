package binary_lifting;

import java.util.List;

/**
 * @author lzhang
 * @since 8/20/20
 */

/*
    Given a tree of N nodes, answer queries of finding the kth ancestor of a given node in O(logN) time,
    return -1 is there is no such ancestor.
    The k-th ancestor of a tree node is the k-th node in the path from this node to root.
 */
public class TreeNodeKthAncestor {
    private int LEVEL;
    private List<Integer>[] adj;
    private int[][] up;

    public TreeNodeKthAncestor(List<Integer>[] adj) {
        this.adj = adj;
        LEVEL = (int)Math.ceil(Math.log(adj.length) / Math.log(2)); //at most log2(N) + 1 levels
        up = new int[adj.length][LEVEL + 1];  //up[node][level]: the 2^level th ancestor of node

        dfs(0, -1);
    }

    //each dfs takes O(logN) time, and there are N of these calls
    private void dfs(int u, int parent) {
        //the 2^0 th ancestor is u's immediate parent
        up[u][0] = parent;
        //the 2^level th ancestor is also the 2^(level - 1) th ancestor of node up[u][level - 1]
        for(int i = 1; i <= LEVEL; i++) {
            if(up[u][i - 1] >= 0) {
                up[u][i] = up[up[u][i - 1]][i - 1];
            }
            else {
                up[u][i] = -1;
            }
        }
        for(int v : adj[u]) {
            if(v != parent) {
                dfs(v, u);
            }
        }
    }


    public int getKthAncestor(int node, int k) {
        int ans = -1;
        //start from the biggest jump 2^LEVEL to the smallest jump 2^0, keep subtracting the current jump from k until k == 0
        //if at any point, the node label < 0, we know from node to root there are not enough nodes
        //to get its kth ancestor
        for(int i = LEVEL; i >= 0; i--) {
            if((1 << i) <= k) {
                node = up[node][i];
                if(node < 0) {
                    break;
                }
                k -= (1 << i);
                if(k == 0) {
                    ans = node;
                    break;
                }
            }
        }
        return ans;
    }
}
