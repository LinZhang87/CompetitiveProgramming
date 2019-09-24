package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzhang
 * @since 5/17/19
 */

/*
This problem was asked by Dropbox.

Given a string s and a list of words words, where each word is the same length, find all starting indices of substrings
in s that is a concatenation of every word in words exactly once.

For example, given s = "dogcatcatcodecatdog" and words = ["cat", "dog"], return [0, 13], since "dogcat" starts at index 0 and "catdog" starts at index 13.

Given s = "barfoobazbitbyte" and words = ["dog", "cat"], return [] since there are no substrings composed of "dog" and "cat" in s.

The order of the indices does not matter.
 */

public class ConcatenatedSubString {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();
        if(words.length == 0) {
            return indices;
        }
        int singleWordLen = words[0].length();
        Map<String, Integer> diff = new HashMap<>();

        for(String word : words) {
            diff.put(word, diff.getOrDefault(word, 0) + 1);
        }

        for(int i = 0; i < singleWordLen; i++) {
            Map<String, Integer> diffCopy = new HashMap<>();
            addIndices(indices, s, words, i, diffCopy);
        }

        return indices;
    }

    private static void addIndices(List<Integer> indices,
                            String s, String[] words,
                            int startIdx, Map<String, Integer> diff) {
        int wordCount = words.length, singleWordLen = words[0].length(), windowLen = wordCount * singleWordLen;
        for(int i = 0; i < wordCount; i++) {
            int j = startIdx + i * singleWordLen;
            String currWord = s.substring(j, j + singleWordLen);
            diff.put(currWord, diff.getOrDefault(currWord, 0) - 1);
            if(diff.get(currWord) == 0) {
                diff.remove(currWord);
            }
        }
        if(diff.size() == 0) {
            indices.add(startIdx);
        }

        for(int i = startIdx + singleWordLen; i + windowLen <= s.length(); i += singleWordLen) {
            String delWord = s.substring(i - singleWordLen, i);
            diff.put(delWord, diff.getOrDefault(delWord, 0) + 1);
            if(diff.get(delWord) == 0) {
                diff.remove(delWord);
            }
            String addWord = s.substring(i + windowLen - singleWordLen, i + windowLen);
            diff.put(addWord, diff.getOrDefault(addWord, 0) - 1);
            if(diff.get(addWord) == 0) {
                diff.remove(addWord);
            }
            if(diff.size() == 0) {
                indices.add(i);
            }
        }
    }


    public static void main(String[] args) {
        //ling mind rabo ofoo owin gdin gbar rwin gmon keyp ound cake
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = {"fooo","barr","wing","ding","wing"};
        List<Integer> indices = findSubstring(s, words);
    }

}
