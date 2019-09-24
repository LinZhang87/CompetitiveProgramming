package math;

/**
 * @author lzhang
 * @since 7/18/19
 */
/*
O(1) time and space
 */
public class Fibonacci {
    public static long nthFibonacci(int n) {
        double PHI = (1 + Math.sqrt(5)) / 2;
        return (long)(Math.pow(PHI, n) / Math.sqrt(5) + 0.5);
    }
    public static void main(String[] args) {
        System.out.println(nthFibonacci(6));
    }
}