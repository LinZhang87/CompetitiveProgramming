package slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 6/25/19
 */

/*
This problem was asked by Microsoft.

Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string.
For example, given the string "abracadabra" and the pattern "abr", you should return [0, 7].
 */

public class AllSubstringPatterns {
    public static List<Integer> allPatterns(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        if(p.length() <= s.length()) {

        }
        return indices;
    }
    public static void main(String[] args) {
        String s = "abracadabra", p = "abr";
    }
}
