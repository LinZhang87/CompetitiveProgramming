package graph.common;

import java.util.*;

/**
 * @author lzhang
 * @since 7/20/19
 */

/*
    Undirected graph minimum spanning tree, assuming the input graph is connected; otherwise an extra check on the min pq size
    should be added.
 */
public class PrimMST {
    /*
        Prim's MST using priority queue;
        The current implementation is incorrect!!
     */
    //edges[i]: {source vertex, destination vertex, edge weight}; n: total vertices
    public List<int[]> primMST(int n, int[][] edges) {

        Map<Integer, List<int[]>> g = constructGraphFromEdges(n, edges);    //int[0]: destination vertex; int[1]: edge weight;

        PriorityQueue<int[]> minPq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));   //int[]: {source vertex, destination vertex, edge weight}

        int[] nodeMinKey = new int[n];  //nodeMinKey[i]: node i's min cost to get included in the spanning tree

        boolean[] spanned = new boolean[n];
        List<int[]> mstEdges = new ArrayList<>();

        Arrays.fill(nodeMinKey, Integer.MAX_VALUE);
        //starting from node 0, all all its edges to minPq
        for(int[] k : g.get(0)) {
            minPq.add(new int[]{0, k[0], k[1]});
        }
        nodeMinKey[0] = 0;
        spanned[0] = true;
        int vertexCnt = 1;  //number of vertices that have been included in mst

        while(vertexCnt < n) {
            int[] curr = minPq.poll();
            //Work around of Java Priority Queue does not support O(1) arbitrary removal
            if(spanned[curr[1]]) {
                continue;
            }
            vertexCnt++;
            mstEdges.add(curr);
            spanned[curr[1]] = true;

            //update keys that might have changed, which are un-spanned nodes of curr[1]'s neighbors
            for(int[] k : g.get(curr[1])) {
                //if neighboring node has been included, skip
                if(spanned[k[0]]) {
                    continue;
                }
                //if after including curr[1], its neighboring node's cost to get included later dropped, update, then add
                //the edge to minPq
                if(k[1] < nodeMinKey[k[0]]) {
                    nodeMinKey[k[0]] = k[1];
                    minPq.add(new int[]{curr[1], k[0], k[1]});
                }
            }
        }
        return mstEdges;
    }

    private Map<Integer, List<int[]>> constructGraphFromEdges(int n, int[][] edges) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        for(int i = 0; i < n; i++) {
            g.put(i, new ArrayList<>());
        }
        for(int[] e : edges) {
            g.get(e[0]).add(new int[]{e[1], e[2]});
            g.get(e[1]).add(new int[]{e[0], e[2]});
        }
        return g;
    }
}
