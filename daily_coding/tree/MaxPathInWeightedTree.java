package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 5/5/19
 */

/*
This problem was asked by Uber.

Given a tree where each edge has a weight, compute the length of the longest path in the tree.

For example, given the following tree:

   a
  /|\
 b c d
    / \
   e   f
  / \
 g   h
and the weights: a-b: 3, a-c: 5, a-d: 8, d-e: 2, d-f: 4, e-g: 1, e-h: 1, the longest path would be c -> a -> d -> f, with a length of 17.

The path does not have to pass through the root, and each node can have any amount of children.
 */

/*
Q: Can I assume all edge weights are > 0?
 */

public class MaxPathInWeightedTree {
    static class TreeNode {
        char name;
        int weightToParent;
        List<TreeNode> children;
        TreeNode(char name, int weightToParent) {
            this.name = name;
            this.weightToParent = weightToParent;
            this.children = new ArrayList<>();
        }
    }
    static class PathResult {
        int maxPath;
        int maxStraightPath;

        PathResult() {}
    }
    public static int maxPathInWeightedTree(TreeNode root) {
        PathResult pathResult = traverse(root);
        return pathResult.maxPath;
    }
    private static PathResult traverse(TreeNode node) {
        PathResult pathResult = new PathResult();
        if(node == null) {
            return pathResult;
        }
        int maxPath = Integer.MIN_VALUE, maxStraightPath = Integer.MIN_VALUE, secondMaxStraightPath = Integer.MIN_VALUE;
        for(TreeNode child : node.children) {
            PathResult result = traverse(child);
            maxPath = Math.max(maxPath, result.maxPath);
            int curr = result.maxStraightPath + child.weightToParent;
            if(curr > maxStraightPath) {
                secondMaxStraightPath = maxStraightPath;
                maxStraightPath = curr;
            }
            else if(curr > secondMaxStraightPath) {
                secondMaxStraightPath = curr;
            }
        }

        maxPath = Math.max(maxPath, (maxStraightPath == Integer.MIN_VALUE ? 0 : maxStraightPath)
                + (secondMaxStraightPath == Integer.MIN_VALUE ? 0 : secondMaxStraightPath));
        pathResult.maxPath = maxPath;
        pathResult.maxStraightPath = maxStraightPath;
        return pathResult;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode('a', 0);
        TreeNode b = new TreeNode('b', 3);
        TreeNode c = new TreeNode('c', 5);
        TreeNode d = new TreeNode('d', 1);
        TreeNode e = new TreeNode('e', 1);
        TreeNode f = new TreeNode('f', 1);
        TreeNode g = new TreeNode('g', 1);
        TreeNode h = new TreeNode('h', 1);

        a.children.add(b); a.children.add(c); a.children.add(d);
        d.children.add(e); d.children.add(f);
        e.children.add(g); e.children.add(h);

        int result = maxPathInWeightedTree(a);
        System.out.println(result);
    }
}
