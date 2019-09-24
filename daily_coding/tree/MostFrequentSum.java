package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 6/10/19
 */

/*
This problem was asked by Apple.

Given the root of a binary tree, find the most frequent subtree sum.
The subtree sum of a node is the sum of all values under a node, including the node itself.

For example, given the following tree:

  5
 / \
2  -5
Return 2 as it occurs twice: once as the left leaf, and once as the sum of 2 + 5 - 5.
 */

public class MostFrequentSum {
    private static int mostFrequentSum = 0, mostOccurences = 0;
    private static Map<Integer, Integer> counts = new HashMap<>();

    public static int getMostFrequentSum(BinaryTreeNode root) {
        preOrderSum(root);
        return mostFrequentSum;
    }
    private static int preOrderSum(BinaryTreeNode node) {
        if(node == null) {
            return 0;
        }
        int sum = node.getVal() + preOrderSum(node.getLeft()) + preOrderSum(node.getRight());
        counts.put(sum, counts.getOrDefault(sum, 0) + 1);
        if(counts.get(sum) > mostOccurences) {
            mostFrequentSum = sum;
            mostOccurences = counts.get(sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(-5);
        node1.setLeft(node2); node1.setRight(node3);

        System.out.println(getMostFrequentSum(node1));
    }
}
