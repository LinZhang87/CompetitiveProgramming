package bitwise;

/**
 * @author lzhang
 * @since 8/2/19
 */

/*
This problem was asked by Salesforce.

Given an array of integers, find the maximum XOR of any two elements.
 */

/*
For simplicity, assume all integers are non-negative for now.
 */
public class MaximumXor {
    class TrieNode {
        int bit;
        TrieNode[] children;

        TrieNode(int bit) {
            this.bit = bit;
            children = new TrieNode[2];
        }
    }
    class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode(0);
        }
        void insertNum(int num) {
            TrieNode currNode = root;
            int mask = 0x80000000;
            for(int i = 0; i < 32; i++) {
                int currBit = (num & mask) != 0 ? 1 : 0;
                if(currNode.children[currBit] == null) {
                    TrieNode newNode = new TrieNode(currBit);
                    currNode.children[currBit] = newNode;
                }
                currNode = currNode.children[currBit];
                mask = (mask >>> 1);
            }
        }
    }
    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        Trie trie = new Trie();
        trie.insertNum(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            maxXor = Math.max(maxXor, maxXorWithNum(trie, nums[i]));
            trie.insertNum(nums[i]);
        }
        return maxXor;
    }
    private int maxXorWithNum(Trie trie, int num) {
        int ans = 0;
        TrieNode currNode = trie.root;
        int mask = 0x80000000;
        for(int i = 0; i < 32; i++) {
            int currBit = (num & mask) != 0 ? 1 : 0;
            if(currNode.children[(currBit + 1) % 2] != null) {
                ans += mask;
                currNode = currNode.children[(currBit + 1) % 2];
            }
            else {
                currNode = currNode.children[currBit];
            }
            mask = (mask >>> 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        MaximumXor maximumXor = new MaximumXor();
        System.out.println(maximumXor.findMaximumXOR(nums));
    }
}
