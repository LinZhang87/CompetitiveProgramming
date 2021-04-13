package tree.traversal;

import tree.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lzhang
 * @since 4/2/20
 */

/*
    Iterative implementation for pre-order, in-order and post-order binary tree traversal
 */
public class BinaryTreeIterativeTraversal {

    public static List<Integer> preOrder(BinaryTreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                //pre-order visits parent node first
                ans.add(curr.val);
                //push curr to stack so we can visit its right subtree after traversing its left subtree
                stack.push(curr);
                //visit left subtree
                curr = curr.left;
            }
            //when curr is null, it means we've already traversed the left subtree, time to visit
            //the right subtree
            else {
                curr = stack.pop();
                curr = curr.right;
            }
        }
        return ans;
    }

    public static List<Integer> inOrder(BinaryTreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                //push curr to stack so we can visit its right subtree after traversing curr then its left subtree
                stack.push(curr);
                //visit left subtree before visiting curr itself
                curr = curr.left;
            }
            //when curr is null, it means we've already traversed the left subtree, time to visit parent node then right subtree
            else {
                curr = stack.pop();
                ans.add(curr.val);
                curr = curr.right;
            }
        }
        return ans;
    }

    public static List<Integer> postOrder(BinaryTreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode prev = null, curr = root;

        while(curr != null || !stack.isEmpty()) {
            //keep going left to visit left subtree until reach null.
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //at this point, left subtree has been visited.
            //the top of the stack stores the parent node. If it has right subtree and
            //this right subtree has not been visited, visit that first; otherwise, either
            //no right subtree or it has been visited, visit the parent node.
            //In post order traversal, a parent node is always visited right after its right child,
            //so we can keep the previously visited node and use it to compare with a parent node's
            //right child. If they are not the same, it means that this is the 1st time we traverse
            //the parent node and we are trying to visit its right child. If they are the same, it
            //means it is our 2nd time visiting the parent node and we just finished traversing
            //the right subtree. It is the parent node's turn to be visited now.
            else {
                BinaryTreeNode top = stack.peek();
                //visit right child first, no need to pop
                if(top.right != null && top.right != prev) {
                    curr = top.right;
                }
                else {
                    //visit parent now, update prev and pop
                    ans.add(top.val);
                    //prev becomes parent
                    prev = top;
                    stack.pop();
                }
            }
        }
        return ans;
    }
}
