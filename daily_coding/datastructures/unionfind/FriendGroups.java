package datastructures.unionfind;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/1/19
 */

/*
A classroom consists of N students, whose friendships can be represented in an adjacency list.
For example, the following descibes a situation where 0 is friends with 1 and 2, 3 is friends with 6, and so on.

{0: [1, 2],
 1: [0, 5],
 2: [0],
 3: [6],
 4: [],
 5: [1],
 6: [3]}
Each student can be placed in a friend group, which can be defined as the transitive closure of that student's friendship relations.
In other words, this is the smallest set such that no student in the group has any friends outside this group.
For the example above, the friend groups would be {0, 1, 2, 5}, {3, 6}, {4}.

Given a friendship list such as the one above, determine the number of friend groups in the class.
 */
public class FriendGroups {
    class UnionFind {
        protected int[] root;
        protected int[] sz;
        protected int count;

        UnionFind(int n) {
            count = n;
            root = new int[n];
            sz = new int[n];
            for(int i = 0; i < n; i++) {
                root[i] = i;
            }
            Arrays.fill(sz, 1);
        }

        int findRoot(int p) {
            if(p != root[p]) {
                root[p] = findRoot(root[p]);
            }
            return root[p];
        }

        void union(int p, int q) {
            int rp = findRoot(p);
            int rq = findRoot(q);
            if(rp == rq) {
                return;
            }
            if(sz[rq] <= sz[rp]) {
                root[rq] = rp;
                sz[rp] += sz[rq];
            }
            else {
                root[rp] = root[rq];
                sz[rq] += sz[rp];
            }
            count--;
        }
    }
    public int numOfFriendGroups(int[][] relations) {
        UnionFind uf = new UnionFind(relations.length);
        for(int i = 0; i < relations.length; i++) {
            for(int j = 0; j < relations[i].length; j++) {
                uf.union(i, relations[i][j]);
            }
        }
        return uf.count;
    }

    public static void main(String[] args) {
        FriendGroups friendGroups = new FriendGroups();
        int[][] relations = {{1,2},{0,5},{0},{6},{},{1},{3}};
        System.out.println(friendGroups.numOfFriendGroups(relations));
    }
}

