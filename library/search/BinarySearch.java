package search;

/**
 * @author lzhang
 * @since 9/3/19
 */

public class BinarySearch {
    public int lowerBoundIdxThatIsGreaterOrEqual(int[] a, int left, int right, int target) {
        if(left > right) {
            return -1;
        }
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(a[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        if(a[left] >= target) {
            return left;
        }
        else if(a[right] >= target) {
            return right;
        }
        return -1;
    }

    public int upperBoundIdxThatIsSmallerOrEqual(int[] a, int left, int right, int target) {
        if(left > right) {
            return -1;
        }
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(a[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid;
            }
        }
        if(a[right] <= target) {
            return right;
        }
        else if(a[left] <= target) {
            return left;
        }
        return -1;
    }
}
