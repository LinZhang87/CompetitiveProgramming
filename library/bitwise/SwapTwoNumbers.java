package bitwise;

/**
 * @author lzhang
 * @since 8/29/19
 */

public class SwapTwoNumbers {
    public void swap(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
    }
}
