package math;

/**
 * @author lzhang
 * @since 1/27/19
 */

public class IntegerExp {
    public double exponential(int x, int y) {
        if(y == 0) {
            return 1;
        }
        else if(y == 1) {
            return x;
        }
        else if(y < 0) {
            return 1 / exponential(x, -y);
        }
        double half = exponential(x, y / 2);
        if(y % 2 != 0) {
            return x * half * half;
        }
        return half * half;
    }

}
