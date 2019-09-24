package tree;

import java.util.Stack;

/**
 * @author lzhang
 * @since 2/14/19
 */

public class DeepestNode {
    private class Node {
        int val;
        Node left;
        Node right;
    }
    private int depth = -1;
    private Node deepestNode;
    public Node getDeepestNodeRecursion(Node root) {
        if(root != null) {
            dfs(root, 0);
        }
        return deepestNode;
    }
    private void dfs(Node node, int d) {
        if(node.left == null && node.right == null && d > depth) {
            depth = d;
            deepestNode = node;
        }
        if(node.left != null) {
            dfs(node.left, d + 1);
        }
        if(node.right != null) {
            dfs(node.right, d + 1);
        }
    }

    private class NodeWithDepth {
        int depth;
        Node node;
        NodeWithDepth(int depth, Node node) {
            this.depth = depth;
            this.node = node;
        }
    }
    public Node getDeepestNodeIterative(Node root) {
        if(root == null) {
            return null;
        }
        Stack<NodeWithDepth> stack = new Stack<>();
        int maxDepth = -1; Node deepestNode = null;
        stack.push(new NodeWithDepth(0, root));
        while(!stack.isEmpty()) {
            NodeWithDepth nodeWithDepth = stack.pop();
            if(nodeWithDepth.node.left == null && nodeWithDepth.node.right == null && nodeWithDepth.depth > maxDepth) {
                maxDepth = nodeWithDepth.depth;
                deepestNode = nodeWithDepth.node;
            }
            if(nodeWithDepth.node.right != null) {
                stack.push(new NodeWithDepth(nodeWithDepth.depth + 1, nodeWithDepth.node.right));
            }
            if(nodeWithDepth.node.left != null) {
                stack.push(new NodeWithDepth(nodeWithDepth.depth + 1, nodeWithDepth.node.left));
            }
        }
        return deepestNode;
    }
}
