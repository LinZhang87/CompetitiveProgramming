package datastructures.balancedbst;

/**
 * @author lzhang
 * @since 2/10/20
 */

/*
    Balanced binary search tree supported operations in O(log N) time:

    search, select, min/max, predecessor/successor, rank, insert, delete

    Red black tree invariants:

    1. each node red or black
    2. root is black
    3. no 2 reds in a row, i.e, red nodes only have black children
    4. every path from root to null has the same number of black nodes

    Claim: every red black tree with N nodes has height <= 2 * log(N + 1),  log is 2 based.
 */

public class RedBlackTree {
    private class Node {
        int v;
        int rank;
    }

    private Node root;

    public RedBlackTree() {
        this.root = null;
    }
}
