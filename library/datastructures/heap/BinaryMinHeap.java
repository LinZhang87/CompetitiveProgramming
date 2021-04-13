package datastructures.heap;

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

    /*
        check if the node's key at current idx is <= both left and right children node. If not, swap with the
        smaller children node and call percolateDown recursively on the swapped child node.
     */
    private void percolateDown(List<HeapNode<T>> nodes, int idx) {
        int minIdx = idx;
        int leftChildIdx = idx * 2 + 1;
        int rightChildIdx = idx * 2 + 2;

        if(leftChildIdx < nodes.size() && nodes.get(leftChildIdx).getKey() < nodes.get(idx).getKey()) {
            minIdx = leftChildIdx;
        }
        if(rightChildIdx < nodes.size() && nodes.get(rightChildIdx).getKey() < nodes.get(idx).getKey()) {
            minIdx = rightChildIdx;
        }
        if(minIdx != idx) {
            HeapNode<T> temp = nodes.get(idx);
            nodes.set(idx, nodes.get(minIdx));
            nodes.set(minIdx, temp);
            percolateDown(nodes, minIdx);
        }
    }

    private void percolateDown(int idx) {
        int minIdx = idx;
        int leftChildIdx = idx * 2 + 1;
        int rightChildIdx = idx * 2 + 2;

        if(leftChildIdx < allNodes.size() && allNodes.get(leftChildIdx).getKey() < allNodes.get(idx).getKey()) {
            minIdx = leftChildIdx;
        }
        if(rightChildIdx < allNodes.size() && allNodes.get(rightChildIdx).getKey() < allNodes.get(idx).getKey()) {
            minIdx = rightChildIdx;
        }
        if(minIdx != idx) {
            nodeIdx.put(allNodes.get(idx), minIdx);
            nodeIdx.put(allNodes.get(minIdx), idx);
            HeapNode<T> temp = allNodes.get(idx);
            allNodes.set(idx, allNodes.get(minIdx));
            allNodes.set(minIdx, temp);
            percolateDown(minIdx);
        }
    }
    private void percolateUp(int idx) {
        if(idx < 0) {
            return;
        }
        HeapNode<T> node = allNodes.get(idx);
        while(idx > 0 && node.getKey() < allNodes.get((idx - 1) / 2).getKey()) {
            allNodes.set(idx, allNodes.get((idx - 1) / 2));
            nodeIdx.put(allNodes.get((idx - 1) / 2), idx);
            idx = (idx - 1) / 2;
        }
        allNodes.set(idx, node);
        nodeIdx.put(node, idx);
    }

    /*
        Add the new node at the end of the node list, then percolate it up
     */
    public void add(HeapNode<T> newNode) {
        allNodes.add(newNode);
        int idx = allNodes.size() - 1;

        percolateUp(allNodes.size() - 1);
    }

    public HeapNode<T> poll() {
        if(allNodes.size() == 0) {
            return null;
        }
        return deleteAtIdx(0);
    }

    private HeapNode<T> deleteAtIdx(int idx) {
        HeapNode<T> deletedNode = allNodes.get(idx);
        if(idx == allNodes.size() - 1) {
            nodeIdx.remove(allNodes.get(allNodes.size() - 1));
            allNodes.remove(idx);
        }
        else {
            allNodes.set(idx, allNodes.get(allNodes.size() - 1));
            nodeIdx.remove(allNodes.get(allNodes.size() - 1));
            allNodes.remove(allNodes.size() - 1);

            if(idx == 0 || allNodes.get(idx).getKey() >= allNodes.get((idx - 1) / 2).getKey()) {
                percolateDown(idx);
            }
            else {
                percolateUp(idx);
            }
        }
        return deletedNode;
    }

    public HeapNode<T> peek() {
        if(allNodes.size() == 0) {
            return null;
        }
        return allNodes.get(0);
    }

    public boolean remove(HeapNode<T> node) {
        if(!nodeIdx.containsKey(node)) {
            return false;
        }
        deleteAtIdx(nodeIdx.get(node));
        return true;
    }
}
