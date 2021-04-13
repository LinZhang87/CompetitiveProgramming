package graph.common;

import java.util.*;

/**
 * @author lzhang
 * @since 11/18/19
 */

/*
    Given a weighted graph with all non-negative weight edges, there are multiple source nodes. Compute the shortest path for all
    non-source nodes to any one of the source node. The distance for source nodes is 0.

    This problem is a generalized version of the Multi Source Shortest Path in Unweighted Graph. If unweighted, we can just put
    all source nodes into a queue, and do a BFS in O(V + E) time.

    In this problem, since we are dealing with weighted graph, we need to use multi source dijkstra shortest path.
 */
public class MultiSourceDijkstra {
    /*
        n nodes; edges[0]: u; edges[1]: v; edges[2]: edge weight; Assume there is no edge[i][0] == edge[j][1] and edge[i][1] == edge[j][0], i != j
        source: all source nodes;
     */
    public int[] multiSourceDijkstra(int n, int[][] edges, int[] source) {
        int[] distance = new int[n];
        boolean[] processed = new boolean[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i = 0; i < source.length; i++) {
            distance[source[i]] = 0;
        }

        PriorityQueue<int[]> minPq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        for(int i = 0; i < source.length; i++) {
            minPq.add(new int[]{0, source[i]});
        }

        List<int[]>[] g = buildGraph(n, edges);

        while(!minPq.isEmpty()) {
            int[] curr = minPq.poll();
            int currNodeDistance = curr[0];
            int currNodeLabel = curr[1];
            if(processed[currNodeLabel]) {
                continue;
            }
            processed[currNodeLabel] = true;
            for(int[] e : g[currNodeLabel]) {
                int neighborNodeLabel = e[0];
                int weight = e[1];
                if(currNodeDistance + weight < distance[neighborNodeLabel]) {
                    distance[neighborNodeLabel] = currNodeDistance + weight;
                    minPq.add(new int[]{distance[neighborNodeLabel], neighborNodeLabel});
                }
            }
        }
        return distance;
    }

    /*
        edges[0]: u; edges[1]: v; edges[2]: edge weight;
     */
    private List<int[]>[] buildGraph(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
            g[e[1]].add(new int[]{e[0], e[2]});
        }
        return g;
    }
}
