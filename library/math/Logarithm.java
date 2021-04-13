package math;

/**
 * @author lzhang
 * @since 10/29/19
 */

public class Logarithm {
    public int log2Floor1(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException();
        }
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public int log2Floor2(int n) {
        return (int)(Math.log(n) / Math.log(2) + 1e-10);
    }
}
