package math;

/**
 * @author lzhang
 * @since 8/28/19
 */

public class Fibonacci {
    public static long NthFibonacciNumber(int n) {
        double PHI = (1 + Math.sqrt(5)) / 2;
        return (long)(Math.pow(PHI, n) / Math.sqrt(5) + 0.5);
    }
    public static void main(String[] args) {
        for(int i = 0; i <= 10; i++) {
            System.out.println(NthFibonacciNumber(i));
        }
    }
}
