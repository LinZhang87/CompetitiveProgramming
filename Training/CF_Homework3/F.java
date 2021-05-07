package practices.homework3;

import java.io.*;
import java.util.*;

/*
    From
    a1 & a2 & a3 & .... & ai - 1 = ai & a(i+1) & a(i + 2) & .... & an (1)
    a1 & a2 & a3 & .... & ai = a(i+1) & a(i + 2) & .... & an    (2)

    Replace (1) with (2), we have a1 & a2 ... & ai-1 = ai & a1 & a2 ..... & ai-1 & ai = (ai & ai) & (a1 & .... ai-1) = ai & (a1 & ...... ai - 1)
    This means ai must be a super set of (a1 & a2 ... & ai-1) in regard with 1 bits.

    So we have the following property:
    1. a2 is a super set of a1;
    2. a3 is a super set of a1 & a2, which is a1;
    3. a4 is a super set of a1 & a2 & a3, which is a1;
    ...........
    n. an is a super set of a1 & a2..... & an-1, which is a1;

    Given that all ai >= 0, we have the following solution:
    (1). Find the min of a and its count;
    (2). Check if this min is a subset of all other numbers in a, if not or there is only 1 such min, the answer is 0;
    (3). For the required condition to hold, we must have the first and last number as min, the order of the rest n - 2 numbers in between
         do not matter. The answer is minVCnt * (minVCnt - 1) * (n - 2)!
         
     Pitfall: if you use int type for minVCnt, minVCnt * (minVCnt - 1) may cause integer overflow so use long!
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
            int[] a = in.nextIntArrayPrimitive(n);
            int minV = Integer.MAX_VALUE;
            long minVCnt = 0;
            for(int v : a) {
                if(v < minV) {
                    minV = v;
                    minVCnt = 1;
                }
                else if(v == minV) {
                    minVCnt++;
                }
            }
            boolean can = true;
            for(int v : a) {
                if((v & minV) != minV) {
                    can = false;
                    break;
                }
            }
            long ans = 0, mod = (long)1e9 + 7;
            if(can && minVCnt > 1) {
                ans = minVCnt * (minVCnt - 1) % mod;
                for(int i = 1; i <= n - 2; i++) {
                    ans = ans * i % mod;
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
