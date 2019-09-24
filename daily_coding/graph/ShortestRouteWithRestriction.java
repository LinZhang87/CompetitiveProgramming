package graph;

import java.util.*;

/**
 * @author lzhang
 * @since 9/16/19
 */

/*
A competitive runner would like to create a route that starts and ends at his house,
with the condition that the route goes entirely uphill at first, and then entirely downhill.

Given a dictionary of places of the form {location: elevation}, and a dictionary mapping paths between some of these locations to their corresponding distances,
find the length of the shortest route satisfying the condition above. Assume the runner's home is location 0.

For example, suppose you are given the following input:

elevations = {0: 5, 1: 25, 2: 15, 3: 20, 4: 10}
paths = {
    (0, 1): 10,
    (0, 2): 8,
    (0, 3): 15,
    (1, 3): 12,
    (2, 4): 10,
    (3, 4): 5,
    (3, 0): 17,
    (4, 0): 10
}
In this case, the shortest valid path would be 0 -> 2 -> 4 -> 0, with a distance of 28.
 */

/*
Algorithm:
1. Find shortest path from home to all other locations with > 0 elevation. The entire path should only use edges with a rising elevation
2. Find shortest path from all other locations with > 0 elevation to home. The entire path should only use edges with a falling elevation (dp can be used)
3. For each location from step 1, if there is a falling elevation only path back to home, add them and update the global shortest path if needed.
 */
public class ShortestRouteWithRestriction {
//    public static int shortestRouteWithRestriction(int[] elevations, int[][] paths) {
//        int n = elevations.length;
//        int[] rising = new int[n];
//        int[] falling = new int[n];
//        Arrays.fill(rising, Integer.MAX_VALUE);
//        Arrays.fill(falling, Integer.MAX_VALUE);
//
//        Map<Integer, List<int[]>> risingGraph = new HashMap<>();
//        Map<Integer, List<int[]>> fallingGraph = new HashMap<>();
//        for(int i = 0; i < n; i++) {
//            risingGraph.put(i, new ArrayList<>());
//            fallingGraph.put(i, new ArrayList<>());
//        }
//
//        //construct graph with only rising/falling paths
//        for(int i = 0; i < paths.length; i++) {
//            if(paths[i][1] > paths[i][0]) {
//                risingGraph.get(paths[i][0]).add(new int[]{paths[i][1], paths[i][2]});
//            }
//            else if(paths[i][1] < paths[i][0]) {
//                fallingGraph.get(paths[i][1]).add(new int[]{paths[i][0], paths[i][2]});
//            }
//        }
//        dijkstra(risingGraph, rising, 0);
//        dijkstra(fallingGraph, falling, 0);
//
//        int res = Integer.MAX_VALUE;
//        for(int i = 1; i < n; i++) {
//            if(rising[i] < Integer.MAX_VALUE && falling[i] < Integer.MAX_VALUE) {
//                res = Math.min(res, rising[i] + falling[i]);
//            }
//        }
//        return res;
//    }
//    private static void dijkstra(Map<Integer, List<int[]>> g, int[] distance, int startNode) {
//        boolean[] processed = new boolean[distance.length];
//        distance[startNode] = 0;
//
//        PriorityQueue<int[]> minPq = new PriorityQueue<>((a1, a2) -> {return a1[0] - a2[0];});
//        minPq.add(new int[]{0, startNode});
//        while(!minPq.isEmpty()) {
//            int[] curr = minPq.poll();
//            int currNodeDistance = curr[0];
//            int currNodeLabel = curr[1];
//            if(processed[currNodeLabel]) {
//                continue;
//            }
//            processed[currNodeLabel] = true;
//            for(int[] edge : g.get(currNodeLabel)) {
//                int neighborNodeLabel = edge[0];
//                int weight = edge[1];
//                if(currNodeDistance + weight < distance[neighborNodeLabel]) {
//                    distance[neighborNodeLabel] = currNodeDistance + weight;
//                    minPq.add(new int[]{distance[neighborNodeLabel], neighborNodeLabel});
//                }
//            }
//        }
//    }

    public static int shortestRouteWithRestriction(int[] elevations, int[][] paths) {
        int n = elevations.length;
        Map<Integer, List<int[]>> risingGraph = new HashMap<>();
        Map<Integer, List<int[]>> fallingGraph = new HashMap<>();
        for(int i = 0; i < n; i++) {
            risingGraph.put(i, new ArrayList<>());
            fallingGraph.put(i, new ArrayList<>());
        }

        //construct graph with only rising/falling paths
        for(int i = 0; i < paths.length; i++) {
            if(paths[i][1] > paths[i][0]) {
                risingGraph.get(paths[i][0]).add(new int[]{paths[i][1], paths[i][2]});
            }
            else if(paths[i][1] < paths[i][0]) {
                fallingGraph.get(paths[i][1]).add(new int[]{paths[i][0], paths[i][2]});
            }
        }
        Stack<Integer> risingOrder = topologicalSort(risingGraph, 0);
        Stack<Integer> fallingOrder = topologicalSort(fallingGraph, 0);

        int[] rising = getDistance(risingGraph, risingOrder);
        int[] falling = getDistance(fallingGraph, fallingOrder);
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            if(rising[i] < Integer.MAX_VALUE && falling[i] < Integer.MAX_VALUE) {
                res = Math.min(res, rising[i] + falling[i]);
            }
        }
        return res;
    }

    private static Stack<Integer> topologicalSort(Map<Integer, List<int[]>> g, int startNode) {
        boolean[] visited = new boolean[g.size()];
        Stack<Integer> stack = new Stack<>();
        topologicalSortHelper(g, startNode, visited, stack);
        return stack;
    }

    private static void topologicalSortHelper(Map<Integer, List<int[]>> g, int currNode, boolean[] visited, Stack<Integer> stack) {
        if(!visited[currNode]) {
            for(int[] edge : g.get(currNode)) {
                topologicalSortHelper(g, edge[0], visited, stack);
            }
            visited[currNode] = true;
            stack.push(currNode);
        }
    }

    private static int[] getDistance(Map<Integer, List<int[]>> g, Stack<Integer> order) {
        int[] distance = new int[g.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        while(!order.isEmpty()) {
            int curr = order.pop();
            for(int[] edge : g.get(curr)) {
                distance[edge[0]] = Math.min(distance[edge[0]], distance[curr] + edge[1]);
            }
        }
        return distance;
    }
//    public static void main(String[] args) {
//        int[] elevations = {5,25,15,20,10};
//        int[][] paths = {{0, 1, 10}, {0, 2, 8}, {0, 3, 15}, {1, 3, 12}, {2, 4, 10}, {3, 4, 5}, {3, 0, 17}, {4, 0, 10}};
//        System.out.println(shortestRouteWithRestriction(elevations, paths));
//    }
}
