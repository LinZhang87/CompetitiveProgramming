import java.util.HashSet;
import java.util.Set;

/**
 * @author lzhang
 * @since 6/18/19
 */


/*
Given a board which is a 2D matrix includes a-z and dictionary dict, find the largest collection of words on the board,
the words can not overlap in the same position. return the size of largest collection.

Example
Give a board below

[['a', 'b', 'c'],
 ['d', 'e', 'f'],
 ['g', 'h', 'i']]

dict = ["abc", "cfi", "beh", "defi", "gh", "ab", "cf", "ih", "gd", "e"]

Return 5        // we can get the largest collection["ab", "cf", "ih", "gd", "e"]

//For simplicity, assume all words only contain lower case English letters

//Extra Challenge: what if each cell can be used more than once? Can you solve it using dp?
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

    public static int boggleGame(char[][] board, Set<String> dict) {
        boolean[][] used = new boolean[board.length][board[0].length];
        Trie trie = constructTrie(dict);
        TrieNode root = trie.root;
        int maxWordCount = 0;

//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[0].length; j++) {
//                if(root.children[board[i][j] - 'a'] != null) {
//                    maxWordCount = Math.max(maxWordCount, boggleGameDfs(board, root.children[board[i][j] - 'a'], used, i, j));
//                }
//            }
//        }
        return maxWordCount;
    }

    /*
    start at (0, 0), either we pick the current character as part of a word or we skip it.
    if we pick it, we append it to the current string buffer, if the current string is not a
    prefix of any words, return; otherwise, if the current string is a word already, dfs on
    choosing the current string as a word; then dfs on keep adding characters;

    if we skip it,
     */
//    private static int boggleGameDfs(char[][] board, TrieNode trieNode, boolean[][] used, int x, int y) {
//        used[x][y] = true;
//        int count = 0;
//        if(trieNode.endOfWord) {
//            count++;
//            for(int i = 0; i < board.length; i++) {
//                for
//            }
//        }
//        else {
//
//        }
//        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
//
//        for(int i = 0; i < 4; i++) {
//            int newX = x + dx[i];
//            int newY = y + dy[i];
//            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
//                    && !used[newX][newY] && trieNode.children[board[newX][newY] - 'a'] != null) {
//                allPossibleWordsDfs(board, trieNode.children[board[newX][newY] - 'a'], words, sb, used, newX, newY);
//            }
//        }
//
//    }

    /*
    Given a dictionary, a method to do a lookup in the dictionary and a M x N board where every cell has one character.
    Find all possible words that can be formed by a sequence of adjacent characters. Note that we can move to any of
    8 adjacent characters, but a word should not have multiple instances of the same cell.
     */
    public static Set<String> getAllPossibleWords(char[][] board, Set<String> dict) {
        Set<String> words = new HashSet<>();
        Trie trie = constructTrie(dict);
        TrieNode root = trie.root;

        boolean[][] used = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(root.children[board[i][j] - 'a'] != null) {
                    //boolean[][] used = new boolean[board.length][board[0].length];
                    allPossibleWordsDfs(board, root.children[board[i][j] - 'a'], words, new StringBuilder(), used, i, j);
                }
            }
        }
        return words;
    }

    //dfs helper method that gets all possible words that following the current TrieNode trieNode
    private static void allPossibleWordsDfs(char[][] board, TrieNode trieNode, Set<String> words,
                                            StringBuilder sb, boolean[][] used, int x, int y) {
        sb.append(trieNode.c);
        used[x][y] = true;
        if(trieNode.endOfWord) {
            words.add(sb.toString());
        }
        int[] dx = {-1,-1,-1,0,0,1,1,1}, dy = {-1,0,1,-1,1,-1,0,1};
        for(int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                    && !used[newX][newY] && trieNode.children[board[newX][newY] - 'a'] != null) {
                allPossibleWordsDfs(board, trieNode.children[board[newX][newY] - 'a'], words, sb, used, newX, newY);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        used[x][y] = false;
    }
    public static void main(String[] args) {
//        char[][] board = {{'a', 'b', 'c'},{'d', 'e', 'f'},{'g', 'h', 'i'}};
//        Set<String> dict = new HashSet<>();
//        dict.add("abc"); dict.add("cfi");dict.add("beh");dict.add("defi");dict.add("gh");
//        System.out.println(boggleGame(board, dict));

        char[][] board = {{'a', 'b', 'c'},{'d', 'e', 'f'},{'g', 'h', 'i'}};
        Set<String> dict = new HashSet<>();
        dict.add("abc"); dict.add("cfi");dict.add("beh");dict.add("defi");dict.add("gh");
        dict.add("ab"); dict.add("cf"); dict.add("ih"); dict.add("gd"); dict.add("e");
        System.out.println(boggleGame(board, dict));

//        Set<String> dict = new HashSet<>();
//        dict.add("geeks"); dict.add("for"); dict.add("quiz"); dict.add("gee");
//        char[][] board = {{'g','i','z'},
//                {'u','e','k'},
//                {'q','s','e'} };
//        getAllPossibleWords(board, dict);
    }
}
