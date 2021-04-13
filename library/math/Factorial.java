package math;
import java.math.BigInteger;

/**
 * @author lzhang
 * @since 9/3/19
 */

public class Factorial {
    // 1 * 2 * 3 ..... * n
    public BigInteger factorial(int n) {
        BigInteger fac = BigInteger.ONE;
        for(int i = 2; i <= n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac;
    }

    // 1 * 2
    public int factorialByIntModule(int n, int mod) {
        BigInteger fac = BigInteger.ONE;
        for(int i = 2; i <= n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac.mod(BigInteger.valueOf(mod)).intValue();
    }
}
