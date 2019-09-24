package tree;

/**
 * @author lzhang
 * @since 7/31/19
 */

/*
Given a binary tree, determine whether or not it is height-balanced.
A height-balanced binary tree can be defined as one in which the heights of the two subtrees of any node never differ by more than one.
 */

public class BalancedBinaryTree {
    class ReturnType {
        int height;
        boolean balanced;

        ReturnType() {

        }
    }

    public boolean heightBalanced(BinaryTreeNode root) {
        return postOrder(root).balanced;
    }

    private ReturnType postOrder(BinaryTreeNode node) {
        ReturnType returnType = new ReturnType();
        if(node == null) {
            returnType.balanced = true;
            return returnType;
        }
        ReturnType left = postOrder(node.getLeft());
        if(!left.balanced) {
            returnType.balanced = false;
            return returnType;
        }
        ReturnType right = postOrder(node.getRight());
        if(!right.balanced) {
            returnType.balanced = false;
            return returnType;
        }
        returnType.balanced = Math.abs(left.height - right.height) <= 1 ? true : false;
        returnType.height = 1 + Math.max(left.height, right.height);
        return returnType;
    }
}
