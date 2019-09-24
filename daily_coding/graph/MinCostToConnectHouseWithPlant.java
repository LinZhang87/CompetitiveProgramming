package graph;

import java.util.*;

/**
 * @author lzhang
 * @since 9/21/19
 */

/*
A group of houses is connected to the main water plant by means of a set of pipes.
A house can either be connected by a set of pipes extending directly to the plant,
or indirectly by a pipe to a nearby house which is otherwise connected.

For example, here is a possible configuration, where A, B, and C are houses, and arrows represent pipes:

A <--> B <--> C <--> plant
Each pipe has an associated cost, which the utility company would like to minimize.
Given an undirected graph of pipe connections, return the lowest cost configuration of pipes such that each house has access to water.

In the following setup, for example, we can remove all but the pipes from plant to A, plant to B, and B to C, for a total cost of 16.

pipes = {
    'plant': {'A': 1, 'B': 5, 'C': 20},
    'A': {'C': 15},
    'B': {'C': 10},
    'C': {}
}
 */


/*
This is a minimum spanning tree problem.
1. sort all edges by their weights in ascending order.
2. create a union find on all houses plus the water plant.
3. iterate through all edges, for each edge, if both vertices are not connected already, add this
    edge into the final configuration and connect them in uf; skip if already connected.
4. check if there is only 1 remaining component, if there is more than 1, it means it is impossible to
connect every house to the water plant.
 */
public class MinCostToConnectHouseWithPlant {
    private class UnionFind {
        private int[] id;
        private int[] sz;
        private int components;

        UnionFind(int N) {
            this.id = new int[N];
            this.sz = new int[N];
            for(int i = 0; i < N; i++) {
                this.id[i] = i;
            }
            Arrays.fill(this.sz, 1);
            components = N;
        }

        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        int find(int p) {
            if(p != this.id[p]) {
                this.id[p] = find(this.id[p]);
            }
            return this.id[p];
        }

        void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if(i == j) {
                return;
            }
            if(this.sz[i] < this.sz[j]) {
                this.id[i] = j;
                this.sz[j] += this.sz[i];
            }
            else {
                this.id[j] = i;
                this.sz[i] += this.sz[j];
            }
            components--;
        }

        int getCountOfComponents() {
            return components;
        }
    }

    public int mstCost(int n, Edge[] plantEdges, Edge[] houseEdges) {
        Edge[] edges = new Edge[plantEdges.length + houseEdges.length];
        for(int i = 0; i < plantEdges.length; i++) {
            edges[i] = plantEdges[i];
        }
        for(int i = plantEdges.length; i < plantEdges.length + houseEdges.length; i++) {
            edges[i] = houseEdges[i - plantEdges.length];
        }
        Arrays.sort(edges, Comparator.comparingInt(Edge::getWeight));

        UnionFind uf = new UnionFind(n);
        int res = 0;
        for(Edge e : edges) {
            if(!uf.connected(e.getV1(), e.getV2())) {
                res += e.getWeight();
                uf.union(e.getV1(), e.getV2());
            }
        }
        if(uf.getCountOfComponents() > 1) {
            return -1;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
