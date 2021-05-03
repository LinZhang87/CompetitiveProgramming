package practices.homework3;

import java.io.*;
import java.util.*;

/*
    1. if total sum S is odd, return 0;
    2. if S is even:
        (a) S / 2 is odd and there is no odd number in a, return 0;
        (b) otherwise if all numbers are even, we can keep dividing all numbers by 2 until there are some odd numbers in a.
            If we did the above operation k times, that means 2^k is the gcd of all numbers.
            we then use knapsack dp to check if there exists a subsequence that sums to S / 2. if there is none, return 0;
            else we need to remove at least 1 element. Since we have done the dividing by 2 preprocessing, there is at least
            1 odd number that we can use to get rid of.

            This is correct because all numbers are a multiple of 2^k. If the total
            sum of a is C * 2^k, then C must be even in order to have 2 subsequences having the same sum. If we remove a number
            that is odd factor * 2^k, it makes the remaining sum to be (C - odd factor) * 2^k, C - odd factor is also odd, making
            it impossible to have have 2 subsequences having the same sum.
 */
public class D {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        //solve(in.nextInt());
        solve(1);
    }

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            int[] a = in.nextIntArrayPrimitiveOneIndexed(n);
            int sum = 0, oddIdx = -1;
            for(int i = 1; i <= n; i++) {
                sum += a[i];
                if(a[i] % 2 != 0 && oddIdx < 0) {
                    oddIdx = i;
                }
            }
            if(sum % 2 != 0) {
                out.println(0);
            }
            else {
                //sum / 2 is odd and there is no odd number in a
                if(sum % 4 != 0 && oddIdx < 0) {
                    out.println(0);
                }
                else {
                    while(oddIdx < 0) {
                        for(int i = 1; i <= n; i++) {
                            a[i] /= 2;
                            if(a[i] % 2 != 0 && oddIdx < 0) {
                                oddIdx = i;
                            }
                        }
                        sum /= 2;
                    }
                    boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
                    dp[0][0] = true;
                    for(int i = 1; i <= n; i++) {
                        for(int j = 0; j <= sum / 2; j++) {
                            if(j >= a[i]) {
                                dp[i][j] |= dp[i - 1][j - a[i]];
                            }
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                    if(!dp[n][sum / 2]) out.println(0);
                    else {
                        out.println(1);
                        out.println(oddIdx);
                    }
                }
            }
        }
        out.close();
    }

    static void initReaderPrinter(boolean test) {
        if (test) {
            try {
                in = new InputReader(new FileInputStream("src/input.in"));
                out = new PrintWriter(new FileOutputStream("src/output.out"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            in = new InputReader(System.in);
            out = new PrintWriter(System.out);
        }
    }

    static class InputReader {
        BufferedReader br;
        StringTokenizer st;

        InputReader(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream), 32768);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        Integer[] nextIntArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitiveOneIndexed(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextInt();
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitiveOneIndexed(int n) {
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextLong();
            return a;
        }

        String[] nextStringArray(int n) {
            String[] g = new String[n];
            for (int i = 0; i < n; i++) g[i] = next();
            return g;
        }
    }
}
