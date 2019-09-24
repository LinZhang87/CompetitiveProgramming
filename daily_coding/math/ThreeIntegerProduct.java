package math;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 2/3/19
 */

public class ThreeIntegerProduct {
    public long largestProduct(int[] a) {
        Arrays.sort(a);
        if(a[0] >= 0 || a[a.length - 1] <= 0 || a[0] < 0 && a[1] >= 0) {
            return a[a.length - 1] * a[a.length - 2] * a[a.length - 3];
        }
        return Math.max(a[a.length - 1] * a[a.length - 2] * a[a.length - 3], a[0] * a[1] * a[a.length - 1]);
    }
}
