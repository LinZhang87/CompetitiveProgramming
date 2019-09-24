package datastructures;

import datastructures.array.HeapNode;

import java.util.*;

/**
 * @author lzhang
 * @since 5/27/19
 */

/*
Min Heap that supports the following operations.

O(log N) add(E e);
O(1) peek();
O(log N) poll();
O(log N) remove(Object o); The PriorityQueue Java library only supports O(N) remove method.
O(1) size()
O(1) contains method
O(N) heapify
 */

//List implementation
public class BinaryMinHeap<T> {
    private List<HeapNode<T>> allNodes;
    private Map<HeapNode<T>, Integer> nodeIdx;

    public BinaryMinHeap() {
        allNodes = new ArrayList<>();
        nodeIdx = new HashMap<>();
    }

    public void heapify(List<HeapNode<T>> nodes) {
        if(nodes == null || nodes.size() <= 1) {
            return;
        }
        for(int i = nodes.size() / 2 - 1; i >= 0; i--) {
            percolateDown(nodes, i);
        }
    }

    private void percolateDown(List<HeapNode<T>> nodes, int idx) {

    }

    public void add() {

    }

    public T poll() {
        return null;
    }

    public T peek() {
        return  null;
    }

    public boolean remove() {
        return true;
    }
}

//Binary tree implementation
