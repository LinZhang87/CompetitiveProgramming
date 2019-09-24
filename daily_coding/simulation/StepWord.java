package simulation;

import java.util.*;

/**
 * @author lzhang
 * @since 8/19/19
 */

/*
A step word is formed by taking a given word, adding a letter, and anagramming the result.
For example, starting with the word "APPLE", you can add an "A" and anagram to get "APPEAL".

Given a dictionary of words and an input word, create a function that returns all valid step words.
 */

/*
For simplicity, assume we only deal with upper case English letters
 */
public class StepWord {
    public static List<String> stepWords(Set<String> dict, String word) {
        int[] map = new int[26];
        List<String> res = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            map[word.charAt(i) - 'A']++;
        }
        for(String w : dict) {
            int[] mapCopy = Arrays.copyOf(map, map.length);
            if(offByOne(mapCopy, word.length(), w)) {
                res.add(w);
            }
        }
        return res;
    }
    private static boolean offByOne(int[] map, int l, String w) {
        if(l != w.length() - 1) {
            return false;
        }
        boolean flag = false;
        for(int i = 0; i < w.length(); i++) {
            map[w.charAt(i) - 'A']--;
            if(map[w.charAt(i) - 'A'] < 0 && flag) {
                return false;
            }
            else if(map[w.charAt(i) - 'A'] < 0) {
                flag = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("APPEAL");
        dict.add("PPAAEL");
        dict.add("PPPAEL");
        dict.add("PPPPEL");
        System.out.println(Arrays.toString(stepWords(dict, "APPLE").toArray(new String[0])));
    }
}
