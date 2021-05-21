package practices.homework5;

import java.io.*;
import java.util.*;

/*
    Greedy Claim: if t1 < t2, then it is always better to process t1 before t2.
    proof: t1 < t2, T1 > T2, then |t1 - T1| + |t2 - T2| >= |t1 - T2| + |t2 - T1| regardless of t1,t2,T1,T2 relative ordering
    So if we sort the input, at a given time, it is optimal to process the earliest unprocessed dish.

    Max Time to Consider: n * 2. Because the max possible dish time is n, so we do not need to go beyond n * 2. Any time bigger
    than n * 2 can be replaced by a time in [n + 1, n * 2] and yield a better answer.

    dp[i][j]: the min cost if the first i dishes are processed and the current time is j.
    at time j, if we take out dish i, dp[i][j] = Math.min(dp[i][j], Math.abs(j - t[i - 1]) + dp[i - 1][j - 1]);
    at time j, if we do not take dish i, then we still need to do this update: dp[i - 1][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
 */

public class F {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            Integer[] t = in.nextIntArray(n);
            Arrays.sort(t);
            //dp[i][j]: the min cost if the first i dishes are processed and the current time is j.
            int[][] dp = new int[n + 1][n * 2 + 1];
            for(int i = 1; i <= n; i++) {
                Arrays.fill(dp[i], 1000000);
            }
            for(int j = 1; j <= n * 2; j++) {
                for(int i = 1; i <= n; i++) {
                    //take out dish i
                    dp[i][j] = Math.min(dp[i][j], Math.abs(j - t[i - 1]) + dp[i - 1][j - 1]);
                    //wait one more minute
                    dp[i - 1][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
            int ans = 1000000;
            for(int j = n; j <= n * 2; j++) {
                ans = Math.min(ans, dp[n][j]);
            }
            out.println(ans);
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
