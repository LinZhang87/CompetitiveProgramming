package practices.homework3;

import java.io.*;
import java.util.*;

/*
    if k is odd, then it is impossible to come back to the starting cell;
    else if k is even, it is always possible to come back to the starting cell.

    The input grid can be viewed as an undirected weighted graph, so a starting cell C (x, y)
    if we know the shortest distance from C to any other cell C'(C' may be C) using k / 2 steps, call it D,
    then the answer for C using k steps to come back is D * 2.

    How do we compute a starting cell's shortest travelling distance using some number of steps? If we have the
    shortest distance travelled starting from cell (x, y) after t steps, and we stop at (x', y'), 
    then it only takes 1 edge's hop for (x, y)'s neighboring cells to get to (x', y') by using t + 1 steps. 
    
    This means if we already have all neighboring cells' shortest travelled distance using t steps, we can use them 
    to compute the current cell's shortest travel distance using t + 1 steps, this value must be coming from one of 
    its four neighboring cell's previous computation results.

    dp[i][j][t]: the shortest distance that can be travelled starting from cell (i, j) using t steps.
    dp[i][j][t] = min { the shortest distance travelled starting from (i, j)'s neighboring cells using t - 1 steps + edge weight}
 */

public class B {
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
            int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
            int[][] w1 = new int[n][m - 1];
            for(int i = 0; i < n; i++) {
                w1[i] = in.nextIntArrayPrimitive(m - 1);
            }
            int[][] w2 = new int[n - 1][m];
            for(int i = 0; i < n - 1; i++) {
                w2[i] = in.nextIntArrayPrimitive(m);
            }
            if(k % 2 != 0) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        out.print(-1 + " ");
                    }
                    out.println();
                }
            }
            else {
                int[][][] dp = new int[n][m][k / 2 + 1];
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        Arrays.fill(dp[i][j], 100000000);
                        dp[i][j][0] = 0;
                    }
                }
                for(int step = 1; step <= k / 2; step++) {
                    for(int i = 0; i < n; i++) {
                        for(int j = 0; j < m; j++) {
                            //move from left
                            if(j > 0) {
                                dp[i][j][step] = Math.min(dp[i][j][step], dp[i][j - 1][step - 1] + w1[i][j - 1]);
                            }
                            //move from right
                            if(j < m - 1) {
                                dp[i][j][step] = Math.min(dp[i][j][step], dp[i][j + 1][step - 1] + w1[i][j]);
                            }
                            //move from top
                            if(i > 0) {
                                dp[i][j][step] = Math.min(dp[i][j][step], dp[i - 1][j][step - 1] + w2[i - 1][j]);
                            }
                            //move from bottom
                            if(i < n - 1) {
                                dp[i][j][step] = Math.min(dp[i][j][step], dp[i + 1][j][step - 1] + w2[i][j]);
                            }
                        }
                    }
                }
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        out.print(dp[i][j][k / 2] * 2 + " ");
                    }
                    out.println();
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
