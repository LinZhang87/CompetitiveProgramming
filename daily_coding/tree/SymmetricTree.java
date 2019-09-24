package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzhang
 * @since 7/22/19
 */

/*
This problem was asked by Amazon.

A tree is symmetric if its data and shape remain unchanged when it is reflected about the root node. The following tree is an example:

        4
      / | \
    3   5   3
  /           \
9              9
Given a k-ary tree, determine whether it is symmetric.
 */

/*
Assumption: Each node has k children nodes; The children are in a set order from left to right, and a particular child node does not exist, it is null.
 */
public class SymmetricTree {
    public boolean isSymmetricBfs(KAryTreeNode root) {
        Queue<KAryTreeNode> q = new LinkedList<>();
        q.add(root);

        while(q.size() > 0) {
            int sz = q.size();
            while(sz > 0) {
                KAryTreeNode node1 = q.poll();
                sz--;
                KAryTreeNode node2 = node1;
                if(sz > 0) {
                    node2 = q.poll();
                    sz--;
                }
                if(node1.getChildren().size() != node2.getChildren().size()) {
                    return false;
                }
                int i = 0;
                int j = node2.getChildren().size() - 1;
                while(i < node1.getChildren().size()) {
                    KAryTreeNode child1 = node1.getChildren().get(i);
                    KAryTreeNode child2 = node2.getChildren().get(j);
                    if(child1 == null && child2 == null) {
                        i++;
                        j--;
                    }
                    else if(child1 == null || child2 == null || child1.getVal() != child2.getVal()) {
                        return false;
                    }
                    else {
                        q.add(child1);
                        q.add(child2);
                        i++;
                        j--;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSymmetricDfs(KAryTreeNode root) {
        return isSymmetricDfsHelper(root, root);
    }

    private boolean isSymmetricDfsHelper(KAryTreeNode node1, KAryTreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 != null && node2 != null && node1.getVal() == node2.getVal()) {
            int i = 0;
            int j = node2.getChildren().size() - 1;
            while(i <= j) {
                if(isSymmetricDfsHelper(node1.getChildren().get(i), node2.getChildren().get(j))) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
