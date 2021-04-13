package datastructures.heap;

import java.util.Objects;

/**
 * @author lzhang
 * @since 9/23/19
 */

/*
For simplicity reason, integer type is used for key. This can be replaced with any types that implement the Comparable interface
 */
public class HeapNode<T> {
    private int key;
    private T value;

    public int getKey() {
        return key;
    }
    public T getValue() {
        return value;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeapNode<?> heapNode = (HeapNode<?>) o;
        return key == heapNode.key &&
                Objects.equals(value, heapNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
