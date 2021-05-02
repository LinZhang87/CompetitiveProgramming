package codeforces.global14;

import java.io.*;
import java.util.*;

/*
    1. First match all left and right socks of the same color at no cost;
    2. For remaining unmatched socks, we need to first check if the left count == right count; 
       if not, we need to pay some cost to make them the same, i.e, converting from left to right or vice versa;
    3. At this point, left count == right count, and there are no shared color; the extra cost is to match colors, which
       is the max count of left and right.
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
            int n = in.nextInt(), l = in.nextInt(), r = in.nextInt();
            int[] c = in.nextIntArrayPrimitive(n);
            int[] lc = new int[n + 1], rc = new int[n + 1];
            for(int i = 0; i < l; i++) {
                lc[c[i]]++;
            }
            for(int i = l; i < n; i++) {
                rc[c[i]]++;
            }
            long ans = 0;
            for(int i = 1; i <= n; i++) {
                int v = Math.min(lc[i], rc[i]);
                lc[i] -= v;
                l -= v;
                rc[i] -= v;
                r -= v;
            }
            int diff = l - r;
            if(diff < 0) {
                diff = -diff;
                diff /= 2;
                for(int i = 1; i <= n && diff > 0; i++) {
                    if(rc[i] > 1) {
                        int v = Math.min(diff, rc[i] / 2);
                        ans += v;
                        diff -= v;
                        r -= v * 2;
                    }
                }
            }
            else if(diff > 0) {
                diff /= 2;
                for(int i = 1; i <= n && diff > 0; i++) {
                    if(lc[i] > 1) {
                        int v = Math.min(diff, lc[i] / 2);
                        ans += v;
                        diff -= v;
                        l -= v * 2;
                    }
                }
            }
            ans += Math.max(l, r);
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
