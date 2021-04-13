package tree.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 7/21/20
 */

public class TreeBuilder {
    /*
        n nodes labelled from 0 to n - 1; root node is 0
     */
    public static TreeNode buildTree(int n, int[][] edges) {
        List<Integer>[] lists = new List[n];
        for(int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            lists[e[0]].add(e[1]);
            lists[e[1]].add(e[0]);
        }
        return buildTreeHelper(lists, 0, -1);
    }

    private static TreeNode buildTreeHelper(List<Integer>[] lists, int node, int parent) {
        TreeNode tn = new TreeNode(node);
        for(int child : lists[node]) {
            if(child != parent) {
                tn.addChildNode(buildTreeHelper(lists, child, node));
            }
        }
        return tn;
    }
}
