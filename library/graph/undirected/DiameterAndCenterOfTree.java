package graph.undirected;

import java.util.*;

/**
 * @author lzhang
 * @since 11/21/19
 */

/*
    Diameter and center of a tree. Given a connected graph with no cycles

Diameter: design a linear-time algorithm to find the longest simple path in the graph.

Center: design a linear-time algorithm to find a vertex such that its maximum distance from any other vertex is minimized.
 */

public class DiameterAndCenterOfTree {

    /*
        BFS: O(N) time and space
        1. pick an arbitrary node s and perform a bfs to find a node t that is the furthest away from s.
        2. starting from t, perform a bfs to find the maximum distance D that is needed to traverse the entire graph, D is the diameter
     */
    public int diameterBFS(Map<Integer, Set<Integer>> g) {
        if(g.size() == 0) {
            return 0;
        }
        int startV = -1;
        for(int k : g.keySet()) {
            startV = k;
            break;
        }
        int[] r = diameterBFSHelper(g, startV);
        return diameterBFSHelper(g, r[1])[0];
    }

    /*
        return an array r of size 2.
        r[0]: is the longest path that starts from startV;
        r[1]: is the node that is the furthest away from startV.
     */
    private int[] diameterBFSHelper(Map<Integer, Set<Integer>> g, int startV) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(startV);
        visited.add(startV);

        int maxDistance = 0, maxDistanceNode = startV;
        while(q.size() > 0) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int v = q.poll();
                for(int w : g.get(v)) {
                    if(!visited.contains(w)) {
                        q.add(w);
                        visited.add(w);
                        maxDistanceNode = w;
                    }
                }
            }
            maxDistance++;
        }
        return new int[]{maxDistance - 1, maxDistanceNode};
    }

    private class PathInfo {
        int simpleMaxPath;  //longest straight path
        int generalMaxPath; //longest general path in a given subgraph
    }
    /*
        DFS: O(N) time and space
        1. pick an arbitrary node v, perform a dfs;
        for each of its unvisited neighboring node, return two computation results:
        a. the longest straight path that ends at w;
        b. the longest general path in the subgraph that starts at w.

        2. after getting all the path infos from its neighbors, do the following update:
        the longest straight path that ends at v: max of all its neighbors' longest straight path + 1
        the longest general path in the subgraph that starts at v:
        max {all of its neighbors' longest general path, 1 + the longest neighboring straight path + the 2nd longest neighboring straight path}

        3. return Math.max(pi.simpleMaxPath, pi.generalMaxPath) - 1 as the the final answer. (need to -1 as the dfs counts node numbers, not edge numbers)
     */
    public int diameterDFS(Map<Integer, Set<Integer>> g) {
        if(g.size() == 0) {
            return 0;
        }
        Set<Integer> visited = new HashSet<>();
        //Assume there is a node labelled 0, if not, just randomly pick one
        visited.add(0);
        PathInfo pi = diameterDFSHelper(g, 0, visited);
        return Math.max(pi.simpleMaxPath, pi.generalMaxPath) - 1;
    }

    private PathInfo diameterDFSHelper(Map<Integer, Set<Integer>> g, int v, Set<Integer> visited) {
        PathInfo pi = new PathInfo();
        int simple = 0, general = 0, first = 0, second = 0;
        for(int w : g.get(v)) {
            if(!visited.contains(w)) {
                visited.add(w);
                PathInfo piw = diameterDFSHelper(g, w, visited);
                general = Math.max(general, piw.generalMaxPath);
                if(piw.simpleMaxPath > first) {
                    second = first;
                    first = piw.simpleMaxPath;
                }
                else if(piw.simpleMaxPath > second) {
                    second = piw.simpleMaxPath;
                }
                simple = Math.max(simple, piw.simpleMaxPath);
            }
        }
        pi.simpleMaxPath = simple + 1;
        pi.generalMaxPath = Math.max(general, first + second + 1);
        return pi;
    }

    /*
        Find a center node: its maximum distance from any other vertex is minimized.
        Center node is the middle node in the path defined by the graph's diameter.
     */
    public int centerOfAcylicUndirectedGraph(Map<Integer, Set<Integer>> g) {
        int startV = -1;
        for(int k : g.keySet()) {
            startV = k;
            break;
        }
        int[] r = diameterBFSHelper(g, startV);
        r = diameterBFSHelper(g, r[1]);
        int diameter = r[0];  //number of edges
        startV = r[1];

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(startV);
        visited.add(startV);

        int level = 0, centerNode = -1;
        boolean found = false;
        while(q.size() > 0) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int v = q.poll();
                if(level == diameter / 2) {
                    centerNode = v;
                    found = true;
                    break;
                }
                for(int w : g.get(v)) {
                    if(!visited.contains(w)) {
                        visited.add(w);
                        q.add(w);
                    }
                }
            }
            level++;
            if(found) {
                break;
            }
        }
        return centerNode;
    }
}