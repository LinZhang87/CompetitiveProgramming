package simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 8/28/19
 */

/*
The "look and say" sequence is defined as follows: beginning with the term 1,
each subsequent term visually describes the digits appearing in the previous term. The first few terms are as follows:

1
11
21
1211
111221
As an example, the fourth term is 1211, since the third term consists of one 2 and one 1.

Given an integer N, print the Nth term of this sequence.
 */
public class LookAndSay {
    public static void lookAndSay(int N) {
        if(N >= 1) {
            List<String> seq = new ArrayList<>();
            seq.add("1");
            N--;
            while(N > 0) {
                String prev = seq.get(seq.size() - 1);
                seq.add(generateNext(prev));
                N--;
            }
            for(int i = 0; i < seq.size(); i++) {
                System.out.println(seq.get(i));
            }
        }
    }
    private static String generateNext(String s) {
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        int i = 0, count = 0;
        while(i < s.length()) {
            if(s.charAt(i) == prev) {
                count++;
            }
            else {
                sb.append(count);
                sb.append(prev);
                prev = s.charAt(i);
                count = 1;
            }
            i++;
        }
        sb.append(count);
        sb.append(prev);
        return sb.toString();
    }
    public static void main(String[] args) {
        lookAndSay(10);
    }
}
