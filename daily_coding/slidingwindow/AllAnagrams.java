package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzhang
 * @since 4/4/19
 */

/*
This problem was asked by Google.

Given a word W and a string S, find all starting indices in S which are anagrams of W.

For example, given that W is "ab", and S is "abxaba", return 0, 3, and 4.

 */

public class AllAnagrams {
    public static List<Integer> getAllAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(p.length() > s.length()) {
            return res;
        }
        Map<Character, Integer> diff = new HashMap<>();
        for(int i = 0; i < p.length(); i++) {
            diff.put(p.charAt(i), diff.getOrDefault(p.charAt(i), 0) + 1);
        }
        for(int i = 0; i < p.length(); i++) {
            diff.put(s.charAt(i), diff.getOrDefault(s.charAt(i), 0) - 1);
            if(diff.get(s.charAt(i)) == 0) {
                diff.remove(s.charAt(i));
            }
        }
        if(diff.size() == 0) {
            res.add(0);
        }
        for(int i = p.length(); i < s.length(); i++) {
            char start = s.charAt(i - p.length()), end = s.charAt(i);
            diff.put(start, diff.getOrDefault(start, 0) + 1);
            if(diff.get(start) == 0) {
                diff.remove(start);
            }
            diff.put(end, diff.getOrDefault(end, 0) - 1);
            if(diff.get(end) == 0) {
                diff.remove(end);
            }
            if(diff.size() == 0) {
                res.add(i - p.length() + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = getAllAnagrams("abcxycabcfacbx", "abc");
        System.out.println(res);
    }
}
