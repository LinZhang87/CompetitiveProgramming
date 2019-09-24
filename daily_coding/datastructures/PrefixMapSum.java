/**
 * @author lzhang
 * @since 7/16/19
 */

/*
This problem was asked by Google.

Implement a PrefixMapSum class with the following methods:

insert(key: str, value: int): Set a given key's value in the map. If the key already exists, overwrite the value.
sum(prefix: str): Return the sum of all values of keys that begin with a given prefix.
For example, you should be able to run the following code:

mapsum.insert("columnar", 3)
assert mapsum.sum("col") == 3

mapsum.insert("column", 2)
assert mapsum.sum("col") == 5
 */

/*
Algorithm: straightforward with trie

Runtime: insert: O(key.length()), two pass; sum: O(prefix.length()), one pass.

Can you do the insert in one pass?

 */

package datastructures;
import java.util.*;

public class PrefixMapSum {
    private class Node {
        char c;
        int val;    //current node value
        int sum;    //sum of the tree whose root is the current node
        Map<Character, Node> children;
        Node(char c) {
            this.c = c;
            this.children = new HashMap<>();
        }
    }
    private Node root;
    public PrefixMapSum() {
        root = new Node('*');
        root.children = new HashMap<>();
    }
    public void insert(String key, int val) {
        Node currNode = root;
        int diff = 0;
        for(int i = 0; i < key.length(); i++) {
            if(!currNode.children.containsKey(key.charAt(i))) {
                currNode.children.put(key.charAt(i), new Node(key.charAt(i)));
            }
            currNode = currNode.children.get(key.charAt(i));
            if(i == key.length() - 1) {
                diff = val - currNode.val;
                currNode.val = val;
            }
        }
        currNode = root;
        for(int i = 0; i < key.length(); i++) {
            currNode = currNode.children.get(key.charAt(i));
            currNode.sum += diff;
        }
    }
    public int sum(String prefix) {
        Node currNode = root;
        for(int i = 0; i < prefix.length(); i++) {
            currNode = currNode.children.get(prefix.charAt(i));
            if(currNode == null) {
                break;
            }
        }
        return currNode == null ? 0 : currNode.sum;
    }

    public static void main(String[] args) {
        PrefixMapSum prefixMapSum = new PrefixMapSum();
        prefixMapSum.insert("columnar", 3);
        System.out.println(prefixMapSum.sum("col"));

        prefixMapSum.insert("column", 2);
        System.out.println(prefixMapSum.sum("col"));
    }
}
