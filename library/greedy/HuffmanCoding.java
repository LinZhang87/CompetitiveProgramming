package greedy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lzhang
 * @since 1/23/20
 */

/*
    Problem description: Given a set of characters and their frequency, construct a binary tree representing a binary
    codes with the following properties.

    1. Prefix-free codes as trees, only leaf nodes are used to represent each character to avoid ambiguity
    2. The average encoding length of the entire tree is minimum

 */
public class HuffmanCoding {
    private class HuffmanTreeNode {
        String c;
        int freq;
        HuffmanTreeNode left, right;

        HuffmanTreeNode(String c, int freq, HuffmanTreeNode left, HuffmanTreeNode right) {
            this.c = c;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int getFreq() {
            return freq;
        }
    }

    /*
        Min Priority Queue implementation, O(N * log N)
     */
    public HuffmanTreeNode buildHuffmanTree1(char[] chars, int[] freq) {
        HuffmanTreeNode[] nodes = constructNodes(chars, freq);
        PriorityQueue<HuffmanTreeNode> minPq = new PriorityQueue<>(Comparator.comparingInt(HuffmanTreeNode::getFreq));
        for(HuffmanTreeNode node : nodes) {
            minPq.add(node);
        }

        while(minPq.size() > 1) {
            HuffmanTreeNode node1 = minPq.poll();
            HuffmanTreeNode node2 = minPq.poll();
            HuffmanTreeNode mergeNode = new HuffmanTreeNode(node1.c + node2.c, node1.freq + node2.freq, node1, node2);
            minPq.add(mergeNode);
        }
        return minPq.peek();
    }

    /*
        Sorting + Two Queue implementation, O(N * log N)
     */
    public HuffmanTreeNode buildHuffmanTree2(char[] chars, int[] freq) {
        HuffmanTreeNode[] nodes = constructNodes(chars, freq);
        Arrays.sort(nodes, Comparator.comparingInt(HuffmanTreeNode::getFreq));
        ArrayDeque<HuffmanTreeNode> q1 = new ArrayDeque<>();
        ArrayDeque<HuffmanTreeNode> q2 = new ArrayDeque<>();
        for(HuffmanTreeNode node : nodes) {
            q1.addLast(node);
        }

        while(!(q1.size() == 1 && q2.size() == 0 || q1.size() == 0 && q2.size() == 1)) {
            HuffmanTreeNode left = null, right = null;
            if(q1.size() > 0 && (q2.size() == 0 || q1.peekFirst().freq <= q2.peekFirst().freq)) {
                left = q1.pollFirst();
            }
            else {
                left = q2.pollFirst();
            }
            if(q1.size() > 0 && (q2.size() == 0 || q1.peekFirst().freq <= q2.peekFirst().freq)) {
                right = q1.pollFirst();
            }
            else {
                right = q2.pollFirst();
            }
            HuffmanTreeNode mergeNode = new HuffmanTreeNode(left.c + right.c, left.freq + right.freq, left, right);
            q2.addLast(mergeNode);
        }
        return q1.size() > 0 ? q1.peekFirst() : q2.peekFirst();
    }

    private HuffmanTreeNode[] constructNodes(char[] chars, int[] freq) {
        HuffmanTreeNode[] nodes = new HuffmanTreeNode[chars.length];
        for(int i = 0; i < chars.length; i++) {
            nodes[i] = new HuffmanTreeNode(String.valueOf(chars[i]), freq[i], null, null);
        }
        return nodes;
    }

    public static void main(String[] args) {
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] freq = {3, 2, 6, 8, 2, 6};
        HuffmanTreeNode root = huffmanCoding.buildHuffmanTree1(chars, freq);
        root = huffmanCoding.buildHuffmanTree2(chars, freq);
        System.out.println();
    }
}
