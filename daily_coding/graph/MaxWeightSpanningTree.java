package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lzhang
 * @since 7/18/19
 */

/*
This problem was asked by Microsoft.

Recall that the minimum spanning tree is the subset of edges of a tree that connect all its vertices
with the smallest possible total edge weight. Given an undirected graph with weighted edges,
compute the maximum weight spanning tree.
 */

public class MaxWeightSpanningTree {
    private class UnionFind {
        private int[] id;
        private int[] sz;

        UnionFind(int N) {
            this.id = new int[N];
            this.sz = new int[N];
            for(int i = 0; i < N; i++) {
                this.id[i] = i;
            }
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
        }
    }
    private UnionFind uf;
    private List<Edge> edges;
    public MaxWeightSpanningTree(List<Edge> edges) {
        int[] bits = new int[edges.size() * 2];
        for(Edge edge : edges) {
            bits[edge.getV1()] = 1;
            bits[edge.getV2()] = 1;
        }
        int count = 0;
        for(int bit : bits) {
            if(bit != 0) {
                count++;
            }
        }
        this.uf = new UnionFind(count);
        this.edges = new ArrayList<>();
        for(Edge edge : edges) {
            this.edges.add(new Edge(edge.getV1(), edge.getV2(), edge.getWeight()));
        }
        Collections.sort(this.edges, Comparator.comparingInt(Edge::getWeight).reversed());
    }
    public List<Edge> maxWeightSpanningTreeUF() {
        int edgeCount = 0;
        List<Edge> maxMST = new ArrayList<>();
        for(Edge edge : this.edges) {
            if(!this.uf.connected(edge.getV1(), edge.getV2())) {
                maxMST.add(edge);
                edgeCount++;
                this.uf.union(edge.getV1(), edge.getV2());
            }
        }
        if(edgeCount < this.uf.id.length - 1) {
            return new ArrayList<>();
        }
        return maxMST;
    }
    public List<Edge> maxWeightSpanningTreeHeap() {
        return null;
    }

    public static void main(String[] args) {
        //MaxWeightSpanningTree maxWeightSpanningTree = new MaxWeightSpanningTree();

    }
}
