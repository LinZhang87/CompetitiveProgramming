package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzhang
 * @since 5/7/19
 */

/*

Daily Coding Problem: Problem #162

This problem was asked by Square.

Given a list of words, return the shortest unique prefix of each word. For example, given the list:

dog
cat
apple
apricot
fish
Return the list:

d
c
app
apr
f

 */

public class ShortestUniquePrefix {
    static class TrieNode {
        char c;
        Map<Character, TrieNode> children;
        int uniqueWord;

        TrieNode () {
            this.children = new HashMap<>();
            this.uniqueWord = 0;
        }
        TrieNode(char c) {
            this.c = c;
            this.children = new HashMap<>();
            this.uniqueWord = 1;
        }
    }

    static class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode();
        }

        void insertWord(String word) {
            TrieNode currNode = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!currNode.children.containsKey(c)) {
                    TrieNode newNode = new TrieNode(c);
                    currNode.children.put(c, newNode);
                }
                else {
                    currNode.children.get(c).uniqueWord++;
                }
                currNode = currNode.children.get(c);
            }
        }

        String getUniquePrefix(String word) {
            boolean unique = false;
            StringBuffer sb = new StringBuffer();
            TrieNode currNode = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                sb.append(c);
                if(!currNode.children.containsKey(c)) {
                    return "";
                }
                if(currNode.children.get(c).uniqueWord == 1) {
                    unique = true;
                    break;
                }
                currNode = currNode.children.get(c);
            }
            return unique ? sb.toString() : "";
        }
    }
    public static List<String> shortestUniquePrefix(List<String> words) {
        List<String> prefix = new ArrayList<>();

        Trie trie = new Trie();
        for(String word : words) {
            trie.insertWord(word);
        }

        for(String word : words) {
            prefix.add(trie.getUniquePrefix(word));
        }
        return prefix;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        //words.add("dog"); words.add("cat"); words.add("apple"); words.add("apricot"); words.add("fish");
        words.add("dog"); words.add("doggy"); words.add("apple"); words.add("apricot"); words.add("lin"); words.add("lin");
        List<String> prefix = shortestUniquePrefix(words);
        System.out.println(prefix);
    }
}
