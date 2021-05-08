package practices.homework2;

import java.io.*;
import java.util.*;

/*
    Application of Sieve of Eratosthenes

    Precompute divisor sums for all numbers from 1 to 10^7 and store it in sum[]. Keep another array ans[], ans[c] stores
    the smallest number n such that its divisor sum equals to c.

    For each number i, add it to all its possible multiples k * i for k >= 1.
    After adding number i's contribution as a divisor to all its multiples, check if ans[sum[i]] has not been updated yet.
    If so, i is the smallest number such that its divisor sum equals to sum[i]. This is true because we are processing i
    from 1 to 10^7.

    This solution takes O(N * logN) time.
 */

public class C {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static int MAX_N = (int)1e7;
    static int[] ans = new int[MAX_N + 1];
    static int[] sum = new int[MAX_N + 1];
    static void precompute() {
        for(int i = 1; i <= MAX_N; i++) {
            for(int j = i; j <= MAX_N; j += i) {
                sum[j] += i;
            }
            if(sum[i] <= MAX_N && ans[sum[i]] == 0) {
                ans[sum[i]] = i;
            }
        }
    }
    static void solve(int testCnt) {
        precompute();
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int c = in.nextInt();
            out.println(ans[c] == 0 ? -1 : ans[c]);
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
