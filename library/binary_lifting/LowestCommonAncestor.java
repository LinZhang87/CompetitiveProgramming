package binary_lifting;

import java.util.List;

/**
 * @author lzhang
 * @since 8/19/20
 */

/*
    O(N * logN) preprocessing time; O(logN) for each LCA query
 */
public class LowestCommonAncestor {
    private int LEVEL, TIMER = 0, N;
    private int[] tin, tout;
    private int[][] up;
    private List<Integer>[] adj;

    public void preprocess(int root) {
        tin = new int[N];
        tout = new int[N];
        LEVEL = (int) (Math.ceil(Math.log(N) / Math.log(2)));
        up = new int[N][LEVEL + 1];
        dfs(root, root);
    }

    private void dfs(int u, int parent) {
        tin[u] = TIMER;
        TIMER++;
        up[u][0] = parent;
        for(int i = 1; i <= LEVEL; i++) {
            up[u][i] = up[up[u][i- 1]][i - 1];
        }
        for(int v : adj[u]) {
            if(v != parent) {
                dfs(v, u);
            }
        }
        tout[u] = TIMER;
        TIMER++;
    }

    private boolean is_ancestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    public int lca(int u, int v) {
        if(is_ancestor(u, v)) {
            return u;
        }
        if(is_ancestor(v, u)) {
            return v;
        }
        //if the current 2^i jump is still not enough to get to a node that is ancestor of v,
        //it means we need to keep jumping upward using smaller steps from up[u][i];
        //otherwise, we either jumped to v or v's ancestor. Either case we need to try smaller
        //jumps. The last jump is 2^0, which is 1 step, so we are guaranteed to find the LCA of u and v
        for(int i = LEVEL; i >= 0; i--) {
            if(!is_ancestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }
}
