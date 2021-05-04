package practices.homework3;

import java.io.*;
import java.util.*;

/*
    precompute dp[i][j]: the length after applying operations j times on a starting digit i;

    for each starting digit i, use 2 rolling size 10 array prev and curr to keep track of the count of
    each digit after the previous and current operation.

    the state transition is: curr[d + 1] = prev[d] for d in [0, 8];
                             curr[0] = prev[9];
                             curr[1] = curr[1] + prev[9];
 */

public class G {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static long mod = (long)1e9 + 7;
    static int maxOp = 200000;
    static long[][] dp = new long[10][maxOp + 1];
    static void preCompute() {
        for(int d = 0; d <= 9; d++) {
            long[] prev = new long[10];
            prev[d] = 1;
            dp[d][0] = 1;
            for(int i = 1; i <= maxOp; i++) {
                long[] curr = new long[10];
                for(int j = 0; j <= 8; j++) {
                    curr[j + 1] = prev[j];
                }
                curr[0] = prev[9];
                curr[1] = (curr[1] + prev[9]) % mod;
                for(int j = 0; j <= 9; j++) {
                    dp[d][i] = (dp[d][i] + curr[j]) % mod;
                }
                prev = curr;
            }
        }
    }
    static void solve(int testCnt) {
        preCompute();
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt(), m = in.nextInt();
            long ans = 0;
            int[] cnt = new int[10];
            while(n > 0) {
                cnt[n % 10]++;
                n /= 10;
            }
            for(int i = 0; i <= 9; i++) {
                ans = (ans + dp[i][m] * cnt[i]) % mod;
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
