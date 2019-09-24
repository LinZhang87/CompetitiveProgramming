package graph;

import tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzhang
 * @since 9/6/19
 */

/*
Two nodes in a binary tree can be called cousins if they are on the same level of the tree but have different parents.
For example, in the following diagram 4 and 6 are cousins.

    1
   / \
  2   3
 / \   \
4   5   6
Given a binary tree and a particular node, find all cousins of that node.
 */

public class CousinNodes {
    public static List<BinaryTreeNode> cousinNodesBfs(BinaryTreeNode root, BinaryTreeNode node) {
        if(root == node) {
            return new ArrayList<>();
        }
        boolean found = false;
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while(q.size() > 0) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                BinaryTreeNode curr = q.poll();
                if(curr.getLeft() != node && curr.getRight() != node) {
                    if(curr.getLeft() != null) {
                        q.add(curr.getLeft());
                    }
                    if(curr.getRight() != null) {
                        q.add(curr.getRight());
                    }
                }
                else {
                    found = true;
                }
            }
            if(found) {
                break;
            }
        }
        return (List)q;
    }

    public static List<BinaryTreeNode> cousinNodesDfs(BinaryTreeNode root, BinaryTreeNode node) {
        List<BinaryTreeNode> cousins = new ArrayList<>();
        if(root == node) {
            return cousins;
        }
        searchGivenNode(null, root, 0, node);
        searchCousinNodes(null, root, 0, cousins);
        return cousins;
    }

    private static int targetDepth;
    private static BinaryTreeNode targetParent;
    private static boolean searchGivenNode(BinaryTreeNode parent, BinaryTreeNode curr, int depth, BinaryTreeNode target) {
        if(curr == null) {
            return false;
        }
        else if(curr == target) {
            targetDepth = depth;
            targetParent = parent;
            return true;
        }
        if(searchGivenNode(curr, curr.getLeft(), depth + 1, target)) {
            return true;
        }
        return searchGivenNode(curr, curr.getRight(), depth + 1, target);
    }

    private static void searchCousinNodes(BinaryTreeNode parent, BinaryTreeNode curr, int depth, List<BinaryTreeNode> cousins) {
        if(curr == null) {
            return;
        }
        else if(parent == targetParent) {
            return;
        }
        else if(depth == targetDepth) {
            cousins.add(curr);
            return;
        }
        searchCousinNodes(curr, curr.getLeft(), depth + 1, cousins);
        searchCousinNodes(curr, curr.getRight(), depth + 1, cousins);
    }

    public static void main(String[] args) {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        BinaryTreeNode n10 = new BinaryTreeNode(10);
        BinaryTreeNode n11 = new BinaryTreeNode(11);
        BinaryTreeNode n12 = new BinaryTreeNode(12);
        n1.setLeft(n2); n1.setRight(n3);
        n2.setLeft(n4); n2.setRight(n5);
        n3.setRight(n6);
        n4.setLeft(n7); n4.setRight(n8);
        n5.setLeft(n9); n5.setRight(n10);
        n6.setLeft(n11);    n6.setRight(n12);

        List<BinaryTreeNode> cousins = cousinNodesBfs(n1, n8);
        for(int i = 0; i < cousins.size(); i++) {
            System.out.println(cousins.get(i).getVal());
        }
        cousins = cousinNodesDfs(n1, n8);
        for(int i = 0; i < cousins.size(); i++) {
            System.out.println(cousins.get(i).getVal());
        }
    }
}
