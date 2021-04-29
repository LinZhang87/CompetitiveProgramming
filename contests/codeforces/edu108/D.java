package codeforces.edu108;

import java.io.*;
import java.util.*;

/*
    O(N^2) sub-arrays to reverse, each reverse takes O(N) to compute sum; O(N^3) too slow;
    Fix a position i as the middle index of a sub-array to be reversed, denote it as [L, R] then it only takes
    O(1) time to get the new sum of [L - 1, R + 1] with i being the middle index. We only need to consider the
    difference contributed by both end numbers at index L - 1 and R + 1.

    Do the above for odd and even length sub-arrays. Each sub-array reverse sum now takes O(1) instead of O(N).
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
            long[] a = in.nextLongArrayPrimitive(n), b = in.nextLongArrayPrimitive(n);
            long noChange = 0;
            for(int i = 0; i < n; i++) {
                noChange += a[i] * b[i];
            }
            long ans = noChange;
            for(int i = 1; i < n - 1; i++) {
                long sum = noChange;
                for(int len = 3; len <= n; len += 2) {
                    int l = i - len / 2, r = i + len / 2;
                    if(l < 0 || r >= n) break;
                    sum += (a[l] * b[r] + a[r] * b[l] - a[l] * b[l] - a[r] * b[r]);
                    ans = Math.max(ans, sum);
                }
            }
            for(int i = 0; i < n - 1; i++) {
                long sum = noChange;
                for(int len = 2; len <= n; len += 2) {
                    int l = i - (len - 1) / 2, r = i + 1 + (len - 1) / 2;
                    if(l < 0 || r >= n) break;
                    sum += (a[l] * b[r] + a[r] * b[l] - a[l] * b[l] - a[r] * b[r]);
                    ans = Math.max(ans, sum);
                }
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
