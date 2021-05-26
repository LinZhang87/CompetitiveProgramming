package codeforces.round722div2;

import java.io.*;
import java.util.*;

/*
    let dp[i] be the number of good pairings of 2 * i points. The answer is dp[n].

    There are two cases for i * 2 points:
    case 1. All segments are the same. If we fix segment's length to be L, then L must be a divisor of i and for a given segment length,
    there is only one unique way to get a good pairing.

    case 2. Not all segments are the same.
    In this case, if there is only 1 max length segment then it must be from point 1 to point i * 2, containing all the rest points. This
    reduces dp[i] to dp[i - 1], with (i - 1) * 2 continuous points in between.

    In fact, we can extend the above to multiple max length segments case. This reduces to dp[i - 2], dp[i - 3],...... dp[1]

    So for case 2, we need to compute the sum from dp[1] to dp[i - 1], we keep a running sum to achieve this efficiently.
    for case 1, we use sieve to compute all number's divisor counts, which takes O(N * logN) time.
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
            long mod = 998244353L;
            int[] sieve = new int[n + 1];
            for(int x = 1; x <= n; x++) {
                for(int u = x; u <= n; u += x) {
                    sieve[u]++;
                }
            }
            long[] dp = new long[n + 1];
            dp[1] = 1;
            long prevSum = 1;
            for(int i = 2; i <= n; i++) {
                dp[i] = (prevSum + sieve[i]) % mod;
                prevSum = (prevSum + dp[i]) % mod;
            }
            out.println(dp[n]);
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