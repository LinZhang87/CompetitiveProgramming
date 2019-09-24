package tree;

/**
 * @author lzhang
 * @since 8/7/19
 */

/*
Recall that a full binary tree is one in which each node is either a leaf node, or has two children. Given a binary tree,
convert it to a full one by removing nodes with only one child.

For example, given the following tree:

         0
      /     \
    1         2
  /            \
3                 4
  \             /   \
    5          6     7
You should convert it to:

     0
  /     \
5         4
        /   \
       6     7
 */
public class RemoveToFullBinaryTree {
    public static BinaryTreeNode removeToFullBinaryTree(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }
        return dfs(root);
    }
    private static BinaryTreeNode dfs(BinaryTreeNode node) {
        if(node.getLeft() == null && node.getRight() == null) {
            return node;
        }
        else if(node.getLeft() == null) {
            return dfs(node.getRight());
        }
        else if(node.getRight() == null) {
            return dfs(node.getLeft());
        }
        node.setLeft(dfs(node.getLeft()));
        node.setRight(dfs(node.getRight()));
        return node;
    }
    public static void main(String[] args) {
        BinaryTreeNode n0 = new BinaryTreeNode(0);
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        n0.setLeft(n1); n0.setRight(n2);
        n1.setLeft(n3);
        n3.setRight(n5);
        n2.setRight(n4);
        n4.setLeft(n6);
        n4.setRight(n7);
        BinaryTreeNode root = removeToFullBinaryTree(n0);
        System.out.println();
    }
}
