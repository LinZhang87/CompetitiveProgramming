package datastructures;

/**
 * @author lzhang
 * @since 5/22/19
 */

/*
weighted quick-union with path compression: that supports amortized O(1) union and find root operations
 */
public class UnionFind {
    private int[] id;   //parent link
    private int[] sz;  //size of component for roots
    private int count;  //number of components

    public UnionFind(int N) {
        this.count = N;
        this.id = new int[N];
        this.sz = new int[N];
        for(int i = 0; i < N; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
        }
    }

    public int getCount() {
        return this.count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        if(p != this.id[p]) {
            this.id[p] = find(this.id[p]);
        }
        return this.id[p];
    }

    public void union(int p, int q) {
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
        this.count--;
    }

    public int getCountInOneComponent(int p) {
        int root = find(p);
        return this.sz[root];
    }
}
