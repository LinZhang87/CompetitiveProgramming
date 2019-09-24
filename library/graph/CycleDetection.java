package graph;

import datastructures.UnionFind;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/9/19
 */

/*
    Given an undirected graph, determine if it contains a cycle.
    Assume there are N nodes labelled from 0 to N - 1.
    And there can be edges that point to a node itself.
    For each node, its edges labels are in increasing order.
 */

public class CycleDetection {
    private class UnionFind {
        private int[] id;   //parent link
        private int[] sz;  //size of component for roots

        UnionFind(int N) {
            this.id = new int[N];
            this.sz = new int[N];
            for(int i = 0; i < N; i++) {
                this.id[i] = i;
                this.sz[i] = 1;
            }
        }

        int find(int p) {
            if(p != this.id[p]) {
                this.id[p] = find(this.id[p]);
            }
            return this.id[p];
        }

        public void union(int i, int j) {
            if(this.sz[i] < this.sz[j]) {
                this.id[i] = j;
                this.sz[j] += this.sz[i];
            }
            else {
                this.id[j] = i;
                this.sz[i] += this.sz[j];
            }
        }
    }
    public boolean cycleDetectionWithUf(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);
        //Assume that each node's edges
        for(int i = 0; i < edges.length; i++) {
            for(int j = 0; j < edges[i].length; j++) {
                //this edge has been processed
                if(edges[i][j] < i) {
                    continue;
                }
                int p = unionFind.find(i);
                int q = unionFind.find(j);
                if(p == q) {
                    return true;
                }
                unionFind.union(p, q);
            }
        }
        return false;
    }

    private final int UNVISITED = 0;
    private final int VISITING = 1;
    private final int VISITED = 2;
    public boolean cycleDetectionWithDfs(int[][] edges) {
        int[] states = new int[edges.length];
        Arrays.fill(states, UNVISITED);
        for(int i = 0; i < edges.length; i++) {
            if(dfs(edges, states, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int[][] edges, int[] states, int node) {
        if(states[node] == VISITED) {
            return false;
        }
        else if(states[node] == VISITING) {
            return true;
        }
        states[node] = VISITING;
        for(int i = 0; i < edges[node].length; i++) {
            if(edges[node][i] < node) {
                continue;
            }
            if(dfs(edges, states, edges[node][i])) {
                return true;
            }
        }
        states[node] = VISITED;
        return false;
    }
}
