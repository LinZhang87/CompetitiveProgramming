package datastructures.array;

/**
 * @author lzhang
 * @since 10/9/19
 */

public class RemoveDuplicatesInSortedArray {
    /*
        arr[] is sorted.
        swap all unique numbers in front and return the index to the last unique number.
     */
    public int removeDuplicates(int[] arr) {
        int slot = 1, next = 1;
        while(next < arr.length) {
            while(next < arr.length && arr[next] == arr[next - 1]) {
                next++;
            }
            if(next < arr.length) {
                arr[slot] = arr[next];
                next++;
                slot++;
            }
        }
        return slot - 1;
    }

    public static void main(String[] args) {
        RemoveDuplicatesInSortedArray removeDuplicatesInSortedArray = new RemoveDuplicatesInSortedArray();
        //int[] arr = {1,1,2,2,3,3,3};
        //int[] arr = {1,2,3};
        //int[] arr = {1,1,1};
        int[] arr = {1};
        removeDuplicatesInSortedArray.removeDuplicates(arr);
    }
}
