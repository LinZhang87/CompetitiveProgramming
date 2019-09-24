package sort;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/5/19
 */

public class MergeSort {
    public void recursiveMergeSortArray(int[] a) {
        int[] aux = new int[a.length];
        recursiveMergeSortArrayHelper(a, aux, 0, a.length - 1);
    }
    private void recursiveMergeSortArrayHelper(int[] a, int[] aux, int low, int high) {
        if(low < high) {
            int mid = low + (high - low) / 2;
            recursiveMergeSortArrayHelper(a, aux, low, mid);
            recursiveMergeSortArrayHelper(a, aux, mid + 1, high);

            //merge left and right sorted array
            for(int k = low; k <= high; k++) {
                aux[k] = a[k];
            }
            int i = low, j = mid + 1;
            for(int k = low; k <= high; k++) {
                if(i > mid) {
                    a[k] = aux[j];
                    j++;
                }
                else if(j > high) {
                    a[k] = aux[i];
                    i++;
                }
                else if(aux[j] < aux[i]) {
                    a[k] = aux[j];
                    j++;
                }
                else {
                    a[k] = aux[i];
                    i++;
                }
            }
        }
    }
    public ListNode recursiveMergeSortLinkedList(ListNode head) {
        int n = 0;
        ListNode curr = head;
        while(curr != null) {
            n++;
            curr = curr.next;
        }
        return recursiveMergeSortLinkedListHelper(head, n);
    }
    private ListNode recursiveMergeSortLinkedListHelper(ListNode head, int sz) {
        if(sz <= 1) {
            return head;
        }
        int mid = sz / 2;
        ListNode prev = null, curr = head;
        int count = 0;
        while(count < mid) {
            prev = curr;
            curr = curr.next;
            count++;
        }
        prev.next = null;
        ListNode left = recursiveMergeSortLinkedListHelper(head, mid);
        ListNode right = recursiveMergeSortLinkedListHelper(curr, sz - mid);
        ListNode dummy = new ListNode(0);
        curr = dummy;
        while(left != null && right != null) {
            if(right.val < left.val) {
                curr.next = right;
                right = right.next;
            }
            else {
                curr.next = left;
                left = left.next;
            }
            curr = curr.next;
        }
        if(left != null) {
            curr.next = left;
        }
        else if(right != null) {
            curr.next = right;
        }
        return dummy.next;
    }

    //Bottom-up
    public void iterativeMergeSortArray(int[] a) {
        int n = a.length;
        int[] aux = new int[n];

        for(int len = 1; len < n; len *= 2) {
            for(int lo = 0; lo < n - len; lo += (len + len)) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                iterativeMergeSortArrayHelper(a, aux, lo, mid, hi);
            }
        }
    }

    private void iterativeMergeSortArrayHelper(int[] a, int[] aux, int lo, int mid, int hi) {
        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) {
                a[k] = aux[j];
                j++;
            }
            else if(j > hi) {
                a[k] = aux[i];
                i++;
            }
            else if(aux[j] < aux[i]) {
                a[k] = aux[j];
                j++;
            }
            else {
                a[k] = aux[i];
                i++;
            }
        }
    }

    public ListNode iterativeMergeSortLinkedList(ListNode head) {
        return null;
    }

    public static void main(String[] args) {
        int[] a = {7,4,8,2,5,1,0,3,9,5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.iterativeMergeSortArray(a);
        System.out.println(Arrays.toString(a));
    }
}
