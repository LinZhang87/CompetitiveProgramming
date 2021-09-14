package practices.homework23_hard;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

/*
    trivial cases:
    1. n == 1, answer is 1;
    2. n == 2, then if abs(a[0] - a[1]) != 1, answer is 2, otherwise answer is 1.

    general cases:
    Key observations: if a sub-array a[i, j] has a m >= 2 that meets the condition, then all the adjacent absolute differences must
    have a gcd > 1. For example, if a[i] % m == a[i + 1] % m == a[i + 2] % m, m >= 2, then it means a[i + 1] - a[i] = k1 * m,
    a[i + 2] - a[i + 1] = k2 * m. The gcd of these 2 differences is at least m. To generalize this, we get all the adjacent absolute differences
    must have a gcd > 1.

    Sparse table:
    With the above observation, we can check all possible sub-array lengths, for each length, it takes O(N) time to get all sub-arrays of this length.
    Use sparse table to precompute range gcds to support efficient sub-array range gcd query in O(log maxV) time.
    Constructing sparse table takes O(N * logN * logMaxV) time.

    Binary search:
    If we check all sub-arrays, too slow! O(N^2 * logMaxV).
    One more observation: if there exists a sub-array of length K that meets the condition, we can ignore all sub-arrays of length < K. This implies binary
    search on the result technique!
 */

public class A {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static class RangeGcdQuery_SparseTable {
        int n, k;
        int[] log;
        long[][] rangeGcd;

        RangeGcdQuery_SparseTable(long[] a) {
            n = a.length;
            log = new int[n + 1];
            log[1] = 0;
            //precompute log
            for(int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }
            k = log[n];
            rangeGcd = new long[n][k + 1];
            for(int i = 0; i < n; i++) {
                rangeGcd[i][0] = a[i];
            }
            //rangeGcd[i][j] is the gcd in range[i, i + 2^j - 1] of length 2^j
            for(int j = 1; j <= k; j++) {
                for(int i = 0; i + (1 << j) <= n; i++) {
                    rangeGcd[i][j] = computeGcd(rangeGcd[i][j - 1], rangeGcd[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        long query(int L, int R) {
            //2^j is at least half the size of range [L, R], at most the entire size of range[L, R]
            int j = log[R - L + 1];
            return computeGcd(rangeGcd[L][j], rangeGcd[R - (1 << j) + 1][j]);
        }

        long computeGcd(long x, long y) {
            if(y == 0) return x;
            return computeGcd(y, x % y);
        }
    }
    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            long[] a = in.nextLongArrayPrimitive(n);
            if(n == 1) {
                out.println(1);
            }
            else {
                int ans = 1;
                for(int i = 1; i < n; i++) {
                    if(abs(a[i] - a[i - 1]) != 1) {
                        ans = 2;
                        break;
                    }
                }
                if(n > 2) {
                    long[] diff = new long[n - 1];
                    for(int i = 1; i < n; i++) {
                        diff[i - 1] = abs(a[i] - a[i - 1]);
                    }
                    RangeGcdQuery_SparseTable st = new RangeGcdQuery_SparseTable(diff);
                    int l = 2, r = n - 1;
                    while(l < r - 1) {
                        int mid = l + (r - l) / 2;
                        if(check(st, mid)) {
                            l = mid;
                        }
                        else {
                            r = mid - 1;
                        }
                    }
                    if(check(st, r)) {
                        ans = max(ans, r + 1);
                    }
                    else if(check(st, l)) {
                        ans = max(ans, l + 1);
                    }
                }
                out.println(ans);
            }
        }
        out.close();
    }

    static boolean check(RangeGcdQuery_SparseTable st, int len) {
        for(int i = 0; i + len <= st.n; i++) {
            if(st.query(i, i + len - 1) > 1) return true;
        }
        return false;
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
