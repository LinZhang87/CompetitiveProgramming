package bitwise;

/**
 * @author lzhang
 * @since 10/3/19
 */

/*
finds the total number of set bits in all integers between 1 and N.
 */

public class CountSetBits {

    /*
        O(N * log N) runtime
     */
    public int countAllSetBits1(int N) {
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            sum += setBitsInOneNumber(i);
        }
        return sum;
    }

    /*
    O(log N) runtime
     */
    private int setBitsInOneNumber(int N) {
        int count = 0;
        while(N > 0) {
            if((N & 1) != 0) {
                count++;
            }
            N = N >>> 1;
        }
        return count;
    }

    /*
        Count all set bits by column(same bit index for all integers in range)
        If N is odd, from 0 to N, there are (N + 1) / 2 even and odd integers respectively;
        For the LSB column, we get (N + 1) / 2 set bits from odd numbers, and the problem is reduced
        compute f(N / 2), but here we need to multiply it by 2 because we'll be count all the set bits
        from 0 to N / 2 twice.
        If N is even, we first count up all the set bits in number N, then solve f(N - 1).
        f(N) = (N + 1) / 2 + 2 * f(N / 2),  N is odd;
        f(N) = set bit count of N + f(N - 1), N is even.

        O(log N * log N) runtime
     */
    public int countAllSetBits2(int N) {
        if(N == 0) {
            return 0;
        }
        else if(N % 2 != 0) {
            return (N + 1) / 2 + 2 * countAllSetBits2(N / 2);
        }
        return setBitsInOneNumber(N) + countAllSetBits2(N - 1);
    }

    public int countAllSetBits3(int N) {
        return 0;
    }
}
