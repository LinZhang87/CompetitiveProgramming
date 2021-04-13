package tree.queries;

import tree.common.BinaryTreeNode;
import tree.common.TreeNode;

/**
 * @author lzhang
 * @since 3/5/20
 */

/*
    Find the lowest common ancestor of two nodes in a binary tree
 */
public class LowestCommonAncestor {
    public static BinaryTreeNode lCA(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        return lCAHelper(root, a, b);
    }
    private static BinaryTreeNode lCAHelper(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        BinaryTreeNode left = lCAHelper(root.left, a, b);
        BinaryTreeNode right = lCAHelper(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public static TreeNode lCAGeneralTree(TreeNode root, TreeNode a, TreeNode b) {
        return lCAGeneralTreeHelper(root, a, b);
    }
    private static TreeNode lCAGeneralTreeHelper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        TreeNode p1 = null, p2 = null;
        for(TreeNode child : root.children) {
            TreeNode res = lCAGeneralTreeHelper(child, a, b);
            if(p1 == null) {
                p1 = res;
            }
            else if(p2 == null) {
                p2 = res;
                break;
            }
        }
        if(p1 != null && p2 != null) {
            return root;
        }
        return p1;
    }

    /*
        Binary Lifting for O(logN) LCA query for two tree nodes
     */

}
