package graph.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author lzhang
 * @since 6/23/20
 */

/*
    Minimum Spanning Tree Using Union Find
 */
public class KruskalMST {
    /*
        undirected graph
        n nodes labelled from 0 to n - 1 and they are connected.
        edges[i][0]: node u of an edge; edges[i][1]: node v of an edge; edges[i][2]: edge weight

        return the edges in a MST.
     */
    class UnionFind {
        int[] root;
        int[] sz;
        UnionFind(int n) {
            root = new int[n];
            sz = new int[n];
            for(int i = 0; i < n; i++) {
                root[i] = i;
                sz[i] = 1;
            }
        }
        int findRoot(int p) {
            if(root[p] != p) {
                root[p] = findRoot(root[p]);
            }
            return root[p];
        }
        boolean union(int p, int q) {
            int rp = findRoot(p);
            int rq = findRoot(q);
            if(rp == rq) return false;
            if(sz[rp] >= sz[rq]) {
                root[rq] = rp;
                sz[rp] += sz[rq];
            }
            else {
                root[rp] = rq;
                sz[rq] += sz[rp];
            }
            return true;
        }
    }
    public List<int[]> kruskalMST(int n, int[][] edges) {
        //Sort all edges by weight in increasing order
        Arrays.sort(edges, Comparator.comparingInt(e->e[2]));
        int edgeCnt = 0;
        List<int[]> mstEdges = new ArrayList<>();
        UnionFind uf = new UnionFind(n);
        //for each edge, if both nodes are not connected, add this edge to mst edges and increment added edge cnt by 1
        for(int i = 0; i < edges.length; i++) {
            if(uf.union(edges[i][0], edges[i][1])) {
                mstEdges.add(edges[i]);
                edgeCnt++;
                if(edgeCnt == n - 1) break;
            }
        }
        return mstEdges;
    }
}
