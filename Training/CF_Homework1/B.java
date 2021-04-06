package tutoring;

import java.io.*;
import java.util.*;

public class B {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static long mod = (long)1e9 + 7;
    static int n, k;
    static long[][] dp;
    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            n = in.nextInt();
            k = in.nextInt();
            dp = new long[n + 1][k + 1];
            for(int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
            for(int i = 0; i <= n; i++) {
                dp[i][1] = 1;
            }
            for(int j = 1; j <= k; j++) {
                dp[n][j] = 1;
            }
            long[] prevPs = new long[n + 1];
            for(int i = 0; i <= n; i++) {
                prevPs[i] = i + 1;
            }
            for(int j = 2; j <= k; j++) {
                for(int i = 0; i < n; i++) {
                    //given i, [i, n - 1] -> [1, n - i]'s prefix sum of j - 1
                    dp[i][j] = (1 + prevPs[n - i] - prevPs[0] + mod) % mod;
                }
                prevPs[0] = dp[0][j];
                for(int i = 1; i <= n; i++) {
                    prevPs[i] = (prevPs[i - 1] + dp[i][j]) % mod;
                }
            }
            out.println(dp[0][k]);
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
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }
}


