package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lzhang
 * @since 6/30/19
 */

/*
This problem was asked by Yelp.

The horizontal distance of a binary tree node describes how far left or right the node will be when the tree is printed out.

More rigorously, we can define it as follows:

The horizontal distance of the root is 0.
The horizontal distance of a left child is hd(parent) - 1.
The horizontal distance of a right child is hd(parent) + 1.
For example, for the following tree, hd(1) = -2, and hd(6) = 0.

             5
          /     \
        3         7
      /  \      /   \
    1     4    6     9
   /                /
  0                8
The bottom view of a tree, then, consists of the lowest node at each horizontal distance.
If there are two nodes at the same depth and horizontal distance, either is acceptable.

For this tree, for example, the bottom view could be [0, 1, 3, 6, 8, 9].

Given the root to a binary tree, return its bottom view.
 */

public class BottomView {
    static class ValAndDepth {
        int val;
        int depth;
        ValAndDepth(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }
    private static Map<Integer, ValAndDepth> map = new TreeMap<>();

    public static List<Integer> bottomView(BinaryTreeNode root) {
        inOrder(root, 0, 0);
        List<Integer> view = new ArrayList<>();
        for(int key : map.keySet()) {
            view.add(map.get(key).val);
        }
        return view;
    }
    private static void inOrder(BinaryTreeNode node, int hd, int depth) {
        if(node != null) {
            inOrder(node.getLeft(), hd - 1, depth + 1);
            ValAndDepth valAndDepth = map.get(hd);
            if(valAndDepth == null || depth > valAndDepth.depth) {
                map.put(hd, new ValAndDepth(node.getVal(), depth));
            }
            inOrder(node.getRight(), hd + 1, depth + 1);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(3);
        BinaryTreeNode node3 = new BinaryTreeNode(7);
        BinaryTreeNode node4 = new BinaryTreeNode(1);
        BinaryTreeNode node5 = new BinaryTreeNode(4);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(9);
        BinaryTreeNode node8 = new BinaryTreeNode(0);
        BinaryTreeNode node9 = new BinaryTreeNode(8);

        node1.setLeft(node2); node1.setRight(node3);
        node2.setLeft(node4); node2.setRight(node5);
        node3.setLeft(node6); node3.setRight(node7);
        node4.setLeft(node8);
        node7.setLeft(node9);

        System.out.println(bottomView(node1));
    }
}
