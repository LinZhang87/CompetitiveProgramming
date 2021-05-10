package codeforces.round636div3;

import java.io.*;
import java.util.*;

/*
    One greedy idea is to count all frequencies of possible pair sums. Then convert all less frequent sums to the most
    frequent sums. This solution is wrong though, one counter example is as follows:

    if we have 3 pairs of (k, k) and 2 pairs of (k - 1, 2). Then converting to sum k * 2 needs 2 * 2 operations but converting
    to sum k + 1 only needs 3 * 1 operations.

    The right approach: we need to check each possible sum x and pick the optimal.
    Fixing x then brute force compute the cost of converting each pair to have sum x takes O(N * K) time, TLE.

    A clever O(N + K) solution:
    1. compute count[], where count[i] is the frequency of pair sum i;
    2. for each pair, compute its possible sum range [l, r] using at most 1 replacement; store this info in range[]
       by doing range[l]++ and range[r + 1]--;
    3. create a prefix sum array ps[] and update it using range[]. ps[i] is the number of pairs that can have sum i by
       using at most one replacement.
    4. iterate over all possible pair sum x, and get the optimal answer: the cost can be divided into 2 cases:
       a. using 1 replacement, ps[i] - cnt[i]
       b. using 2 replacement, (n / 2 - ps[i]) * 2

 */

public class D {
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
            int n = in.nextInt(), k = in.nextInt();
            int[] a = in.nextIntArrayPrimitive(n);
            int[] cnt = new int[k * 2 + 1];
            for(int i = 0; i < n / 2; i++) {
                cnt[a[i] + a[n - 1 - i]]++;
            }
            int[] range = new int[k * 2 + 2];
            for(int i = 0; i < n / 2; i++) {
                int l = Math.min(a[i], a[n - 1 - i]) + 1;
                int r = Math.max(a[i], a[n - 1 - i]) + k;
                range[l]++;
                range[r + 1]--;
            }
            int[] ps = new int[k * 2 + 2];
            for(int i = 2; i <= k * 2 + 1; i++) {
                ps[i] = ps[i - 1] + range[i];
            }
            int ans = n / 2;
            for(int x = 2; x <= k * 2; x++) {
                ans = Math.min(ans, ps[x] - cnt[x] + (n / 2 - ps[x]) * 2);
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
