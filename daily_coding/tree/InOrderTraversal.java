package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lzhang
 * @since 7/7/19
 */

/*
This problem was asked by Palantir.

Typically, an implementation of in-order traversal of a binary tree has O(h) space complexity,
where h is the height of the tree. Write a program to compute the in-order traversal of a binary tree using O(1) space.
 */

/*
Algorithm: if the nodes in the given binary tree only have left and right child reference, O(1) space is impossible, or is it?
Option 1 is to augment the binary tree node to have parent node.
Option 2 is to use threaded binary tree.
 */

public class InOrderTraversal {
    public static List<Integer> inOrderTraversal(BinaryTreeNode root) {
        List<Integer> vals = new ArrayList<>();
        if(root == null) {
            return vals;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;

        while(stack.size() > 0 || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            curr = stack.pop();
            vals.add(curr.getVal());
            curr = curr.getRight();
        }
        return vals;
    }
    public static List<Integer> inOrderTraversalConstantSpace(BinaryTreeNode root) {
        List<Integer> vals = new ArrayList<>();
        BinaryTreeNode curr = root;

        while(curr != null) {
            //add val if there is no left node to go to
            if(curr.getLeft() == null) {
                vals.add(curr.getVal());
                curr = curr.getRight();
            }
            //make the rightmost descendant of curr's left child points to curr
            else {
                BinaryTreeNode rightMostDesc = curr.getLeft();
                while(rightMostDesc.getRight() != null && rightMostDesc.getRight() != curr) {
                    rightMostDesc = rightMostDesc.getRight();
                }
                if(rightMostDesc.getRight() == null) {
                    rightMostDesc.setRight(curr);
                    curr = curr.getLeft();
                }
                else {
                    rightMostDesc.setRight(null);
                    vals.add(curr.getVal());
                    curr = curr.getRight();
                }
            }
        }
        return vals;
    }
    public static void main(String[] args) {
        BinaryTreeNode node0 = new BinaryTreeNode(0);
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node8 = new BinaryTreeNode(8);
        BinaryTreeNode node9 = new BinaryTreeNode(9);
        BinaryTreeNode node10 = new BinaryTreeNode(10);

        node0.setLeft(node1); node0.setRight(node2);
        node1.setLeft(node3); node1.setRight(node4);
        node2.setLeft(node5); node2.setRight(node6);
        node3.setLeft(node7);
        node4.setRight(node8);
        node5.setRight(node9);
        node6.setLeft(node10);

        List<Integer> vals = inOrderTraversal(node0);
        //[7,3,1,4,8,0,5,9,2,10,6]
        System.out.println(vals.toString());
        vals = inOrderTraversalConstantSpace(node0);
        System.out.println(vals.toString());
    }
}
