package datastructures.hasing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lzhang
 * @since 9/3/19
 */

/*
A wall consists of several rows of bricks of various integer lengths and uniform height.
Your goal is to find a vertical line going from the top to the bottom of the wall that cuts through the fewest number of bricks.
If the line goes through the edge between two bricks, this does not count as a cut.

For example, suppose the input is as follows, where values in each row represent the lengths of bricks in that row:

[[3, 5, 1, 1],
 [2, 3, 3, 2],
 [5, 5],
 [4, 4, 2],
 [1, 3, 3, 3],
 [1, 1, 6, 1, 1]]
The best we can we do here is to draw a line after the eighth brick, which will only require cutting through the bricks in the third and fifth row.

Given an input consisting of brick lengths for each row such as the one above, return the fewest number of bricks that must be cut to create a vertical line.
 */

/*
Your solution:
for each row, computes all the brick edge distance in O(M) time, M is the length of the wall.
for each possible brick edge, save it in a separate set. after computing all edges distances for all
rows, iterate through all possible cut candidates, if a row does not contain this candidate cut index,
it means having a vertical line at this index will cut through one brick of this row.

Assume there are N rows and B bricks in total, the length of the wall is M. O(M) space. O(B) + O(B * N) runtime

Editorial Solution:
Use a hashmap to store the count of all possible edge distances, the answer is the number of rows minus the max count.
 */
public class MinNumBrickCut {
    public static int minNumBrickCut(int[][] bricks) {
        int n = bricks.length;
        Set<Integer> allPossibleCutIdx = new HashSet<>();
        Set<Integer>[] rowIdx = new Set[n];

        for(int i = 0; i < n; i++) {
            rowIdx[i] = new HashSet<>();
            int currSum = 0;
            for(int j = 0; j < bricks[i].length - 1; j++) {
                currSum += bricks[i][j];
                rowIdx[i].add(currSum);
                allPossibleCutIdx.add(currSum);
            }
        }
        int minCut = n;
        for(int cutIdx : allPossibleCutIdx) {
            int currCut = 0;
            for(int i = 0; i < n; i++) {
                if(!rowIdx[i].contains(cutIdx)) {
                    currCut++;
                }
            }
            minCut = Math.min(minCut, currCut);
        }
        return minCut;
    }

    public static int minNumBrickCutMap(int[][] bricks) {
        int n = bricks.length;
        int wallLen = 0;
        for(int i = 0; i < bricks[0].length; i++) {
            wallLen += bricks[0][i];
        }
        //Map<Integer, Integer> edgeMap = new HashMap<>();
        int[] edgeMap = new int[wallLen];
        int max = 0;
        for(int i = 0; i < n; i++) {
            int currSum = 0;
            for(int j = 0; j < bricks[i].length - 1; j++) {
                currSum += bricks[i][j];
                //edgeMap.put(currSum, edgeMap.getOrDefault(currSum, 0) + 1);
                edgeMap[currSum] += 1;
                max = Math.max(max, edgeMap[currSum]);
            }
        }
        return n - max;
    }
    public static void main(String[] args) {
        int[][] bricks = {{3, 5, 1, 1},
                {2, 3, 3, 2},
                {5, 5},
                {4, 4, 2},
                {1, 3, 3, 3},
                {1, 1, 6, 1, 1}};
        System.out.println(minNumBrickCutMap(bricks));
    }
}
