package backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lzhang
 * @since 7/23/19
 */

/*
This problem was asked by Uber.

One way to unlock an Android phone is through a pattern of swipes across a 1-9 keypad.

For a pattern to be valid, it must satisfy the following:

All of its keys must be distinct.
It must not connect two keys by jumping over a third key, unless that key has already been used.
For example, 4 - 2 - 1 - 7 is a valid pattern, whereas 2 - 1 - 7 is not.

Find the total number of valid unlock patterns of length N, where 1 <= N <= 9.

Extra Challenge: Find all valid unlock patterns.


1       2       3


4       5       6


7       8       9
 */

/*
at any given point, as long as not used, free to jump diagonally;
if its horizontal/vertical adjacent neighbors are not used, you have to
use it if you want to go horizontally or vertically. Otherwise, can jump
over.

Only need to compute all valid patterns that start from 1, 2 and 5.
 */

public class PatternOfSwipes {
    private static int[][] delta = {{-1,0},{-2,1},{-1,1},{-1,2},{0,1},{1,2},{1,1},{2,1},{1,0},{2,-1},{1,-1},{1,-2},{0,-1},{-1,-2},{-1,-1},{-2,-1}};
    public static int totalSwipePatterns(int[][] pad) {
        boolean[][] used = new boolean[3][3];
        used[0][0] = true;
        int count1 = dfs(0, 0, used);
        used = new boolean[3][3];
        used[0][1] = true;
        int count2 = dfs(0, 1, used);
        used = new boolean[3][3];
        used[1][1] = true;
        int count3 = dfs(1,1, used);
        return count1 * 4 + count2 * 4 + count3;
    }
    private static int dfs(int currX, int currY, boolean[][] used) {
        int count = 0;
        if(currX >= 0 && currX < 3 && currY >= 0 && currY < 3) {
            count++;
        }
        for(int i = 0; i < delta.length; i++) {
            int newX = currX + delta[i][0];
            int newY = currY + delta[i][1];
            while(newX >= 0 && newX < 3 && newY >= 0 && newY < 3 && used[newX][newY]) {
                newX += delta[i][0];
                newY += delta[i][1];
            }
            if(newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                used[newX][newY] = true;
                count += dfs(newX, newY, used);
                used[newX][newY] = false;
            }
        }
        return count;
    }

    public static int numOfPaths(int currNum, Map<Integer, Integer> jumpMap, Set<Integer> used) {
        int count = 1;
        for(int nextNum = 1; nextNum <= 9; nextNum++) {
            if(!used.contains(nextNum)) {
                int key = (1 << currNum) | (1 << nextNum);
                if(!jumpMap.containsKey(key) || used.contains(jumpMap.get(key))) {
                    used.add(nextNum);
                    count += numOfPaths(nextNum, jumpMap, used);
                    used.remove(nextNum);
                }
            }
        }
        return count;
    }

    public static int unlockCombinations() {
        Map<Integer, Integer> jumpMap = new HashMap<>();
        jumpMap.put((1 << 1) | (1 << 3), 2);
        jumpMap.put((1 << 1) | (1 << 7), 4);
        jumpMap.put((1 << 1) | (1 << 9), 5);
        jumpMap.put((1 << 2) | (1 << 8), 5);
        jumpMap.put((1 << 3) | (1 << 9), 6);
        jumpMap.put((1 << 3) | (1 << 7), 5);
        jumpMap.put((1 << 4) | (1 << 6), 5);
        jumpMap.put((1 << 7) | (1 << 9), 8);

        Set<Integer> used = new HashSet<>();
        int total = 0;
        used.add(1);
        total += 4 * numOfPaths(1, jumpMap, used);
        used = new HashSet<>();
        used.add(2);
        total += 4 * numOfPaths(2, jumpMap, used);
        used = new HashSet<>();
        used.add(5);
        total += numOfPaths(5, jumpMap, used);

        return total;
    }
    public static void main(String[] args) {
        int[][] pad = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(totalSwipePatterns(pad));

        System.out.println(unlockCombinations());
    }
}
