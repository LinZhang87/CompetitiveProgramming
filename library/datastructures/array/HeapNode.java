package datastructures.array;

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
}
