package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzhang
 * @since 7/12/19
 */

/*
This problem was asked by Facebook.

Boggle is a game played on a 4 x 4 grid of letters.
The goal is to find as many words as possible that can be formed by a sequence of adjacent letters in the grid,
using each cell at most once. Given a game board and a dictionary of valid words, implement a Boggle solver.
 */

public class BoggleGame {
    static class TrieNode {
        char c;
        TrieNode[] children;
        boolean endOfWord;

        TrieNode(char c) {
            this.c = c;
            this.children = new TrieNode[26];
            this.endOfWord = false;
        }
    }
    static class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode('#');
        }

        void addWord(String word) {
            TrieNode currNode = root;
            for(int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(currNode.children[currChar - 'a'] == null) {
                    TrieNode newNode = new TrieNode(currChar);
                    currNode.children[currChar - 'a'] = newNode;
                    currNode = newNode;
                }
                else {
                    currNode = currNode.children[currChar - 'a'];
                }
                if(i == word.length() - 1) {
                    currNode.endOfWord = true;
                }
            }
        }

        boolean search(String word) {
            TrieNode currNode = root;
            for(int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(currNode.children[currChar - 'a'] == null) {
                    return false;
                }
                currNode = currNode.children[currChar - 'a'];
                if(i == word.length() - 1 && !currNode.endOfWord) {
                    return false;
                }
            }
            return true;
        }

        boolean startsWith(String prefix) {
            TrieNode currNode = root;
            for(int i = 0; i < prefix.length(); i++) {
                char currChar = prefix.charAt(i);
                if(currNode.children[currChar - 'a'] == null) {
                    return false;
                }
                currNode = currNode.children[currChar - 'a'];
            }
            return true;
        }
    }

    private static Trie constructTrie(Set<String> dict) {
        Trie trie = new Trie();
        for(String word : dict) {
            trie.addWord(word);
        }
        return trie;
    }
    public static List<String> boggleGame(char[][] board, Set<String> dict) {
        boolean[][] used = new boolean[board.length][board[0].length];
        Trie trie = constructTrie(dict);
        TrieNode root = trie.root;
        List<String> res = new ArrayList<>();

        return res;
    }
    public static void main(String[] args) {
        char[][] board = {{'a', 'b', 'c'},{'d', 'e', 'f'},{'g', 'h', 'i'}};
        Set<String> dict = new HashSet<>();
        dict.add("abc"); dict.add("cfi");dict.add("beh");dict.add("defi");dict.add("gh");
        dict.add("ab"); dict.add("cf"); dict.add("ih"); dict.add("gd"); dict.add("e");
        System.out.println(boggleGame(board, dict));
    }
}


