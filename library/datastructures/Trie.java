package datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 6/19/19
 */

public class Trie {
    private class TrieNode {
        private char c;
        private Map<Character, TrieNode> children;
        private boolean isWord;

        public TrieNode() {
            this.children = new HashMap<>();
        }

        public TrieNode(char c) {
            this.c = c;
            this.children = new HashMap<>();
        }

        public Map<Character, TrieNode> getChildren() {
            return this.children;
        }

        public void setEndOfWord() {
            this.isWord = true;
        }

        public boolean getEndOfWord() {
            return this.isWord;
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currNode = root;
        for(int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if(!currNode.getChildren().containsKey(currChar)) {
                TrieNode newNode = new TrieNode(currChar);
                currNode.getChildren().put(currChar, newNode);
            }
            currNode = currNode.getChildren().get(currChar);

            if(i == word.length() - 1) {
                currNode.setEndOfWord();
            }
        }
    }

    public boolean search(String word) {
        TrieNode currNode = root;
        for(int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if(!currNode.getChildren().containsKey(currChar)) {
                return false;
            }
            currNode = currNode.getChildren().get(currChar);

            if(i == word.length() - 1 && !currNode.getEndOfWord()) {
                return false;
            }
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        TrieNode currNode = root;
        for(int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            if(!currNode.getChildren().containsKey(currChar)) {
                return false;
            }
            currNode = currNode.getChildren().get(currChar);
        }
        return true;
    }
}
