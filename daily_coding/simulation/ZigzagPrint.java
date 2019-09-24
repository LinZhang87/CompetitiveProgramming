package simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 8/6/19
 */

/*
Given a string and a number of lines k, print the string in zigzag form.
In zigzag, characters are printed out diagonally from top left to bottom right until reaching the kth line, then back up to top right, and so on.

For example, given the sentence "thisisazigzag" and k = 4, you should print:

t     a     g
 h   s z   a
  i i   i z
   s     g
 */

/*
for the 0th row, start = 0, diff = (k - 1) * 2
for the 1st to (k - 2)th row, start = i, diff = (k - 1) * 2 - 2 * i and 2 * i alternating
for the (k - 1)th row, start = k - 1, diff = (k - 1) * 2.

corner case: when k == 1, print the input string.
 */
public class ZigzagPrint {
    public static void print(String s, int k) {
        if(k == 1) {
            System.out.println(s);
            return;
        }
        int n = s.length();
        List<List<Integer>> lists = new ArrayList<>();

        int start = 0;
        List<Integer> firstRow = new ArrayList<>();
        for(int i = start; i < n; i += (k - 1) * 2) {
            firstRow.add(i);
        }
        lists.add(firstRow);
        start++;
        while(start < k - 1) {
            int i = start;
            boolean alternate = true;
            List<Integer> currRow = new ArrayList<>();
            while(i < n) {
                currRow.add(i);
                i += (alternate ? (k - 1) * 2 - 2 * start : 2 * start);
                alternate = !alternate;
            }
            lists.add(currRow);
            start++;
        }
        List<Integer> lastRow = new ArrayList<>();
        for(int i = start; i < n; i += (k - 1) * 2) {
            lastRow.add(i);
        }
        lists.add(lastRow);

        for(int i = 0; i < lists.size(); i++) {
            List<Integer> currList = lists.get(i);
            if(currList.size() == 0) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            int prev = -1;
            for(int j = 0; j < currList.size(); j++) {
                for(int t = 0; t < (currList.get(j) - prev - 1); t++) {
                    sb.append(" ");
                }
                sb.append(s.charAt(currList.get(j)));
                prev = lists.get(i).get(j);
            }
            System.out.println(sb.toString());
        }
    }
    public static void main(String[] args) {
        print("thisisazigzag", 8);
    }
}
