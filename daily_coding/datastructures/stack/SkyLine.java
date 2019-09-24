package datastructures.stack;

import java.util.*;

/**
 * @author lzhang
 * @since 9/12/19
 */

/*
The skyline of a city is composed of several buildings of various widths and heights,
possibly overlapping one another when viewed from a distance.
We can represent the buildings using an array of (left, right, height) tuples,
which tell us where on an imaginary x-axis a building begins and ends, and how tall it is.
The skyline itself can be described by a list of (x, height) tuples, giving the locations at which the height visible to a distant observer changes, and each new height.

Given an array of buildings as described above, create a function that returns the skyline.

For example, suppose the input consists of the buildings [(0, 15, 3), (4, 11, 5), (19, 23, 4)]. In aggregate, these buildings would create a skyline that looks like the one below.

     ______
    |      |        ___
 ___|      |___    |   |
|   |   B  |   |   | C |
| A |      | A |   |   |
|   |      |   |   |   |
------------------------
As a result, your function should return [(0, 3), (4, 5), (11, 3), (15, 0), (19, 4), (23, 0)]
 */

public class SkyLine {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        int[][] augBuildings = new int[buildings.length * 2][3];
        int j = 0;
        for(int i = 0; i < buildings.length; i++) {
            augBuildings[j] = buildings[i];
            augBuildings[j + 1] = new int[]{buildings[i][1], buildings[i][1], 0};
            j += 2;
        }
        Arrays.sort(augBuildings,
                (b1, b2) ->
                {
                    if(b1[0] != b2[0]) {
                        return b1[0] - b2[0];
                    }
                    return b2[2] - b1[2];
                }
        );
        PriorityQueue<int[]> maxPq = new PriorityQueue<>((b1, b2) -> {
            return b1[0] - b2[0];
        });
        for(int i = 0; i < augBuildings.length; i++) {
            while(maxPq.size() > 0 && augBuildings[i][0] >= maxPq.peek()[1]) {
                maxPq.poll();
            }
            maxPq.add(new int[]{-augBuildings[i][2], augBuildings[i][1]});
            if(res.size() == 0 || res.get(res.size() - 1).get(1) != -maxPq.peek()[0]) {
                List<Integer> line = new ArrayList<>();
                line.add(augBuildings[i][0], -maxPq.peek()[0]);
                res.add(line);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        SkyLine skyLine = new SkyLine();
        //[[1,3],[2,0]]
        skyLine.getSkyline(buildings);
    }
}
