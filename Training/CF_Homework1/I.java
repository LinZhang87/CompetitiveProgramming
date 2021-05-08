package practices.homework1;

import java.io.*;
import java.util.*;

/*
    if n % k == 0, then simply divide into k numbers with each being n / k, LCM == n / k <= n / 2 as k >= 3;
    if n % k != 0, then we can piggy-back on how we solve the easier version where k == 3. Here we first get (k - 3) 1,
    leaving us a remaining sum S = n - (k - 3). We then apply the following strategy:
    
    1. S % 2 != 0,  we have 1, (S - 1) / 2, (S - 1) / 2.
    2. S % 4 == 0, we have S / 4, S / 4, S / 2.
    3. S % 2 == 0 && S % 4 != 0, we have 2, S / 2 - 1, S / 2 - 1.  S >= 3 since we still need to assign 3 positive integers.
    in case 3, S >= 6 and S / 2 must be odd, so S / 2 - 1 must be even and is >= 2, so the LCM is S / 2 - 1 <= n / 2.
 */

public class I {
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
            if(n % k == 0) {
                for(int i = 0; i < k; i++) {
                    out.print( n / k + " ");
                }
            }
            else {
                for(int i = 0; i < k - 3; i++) {
                    out.print(1 + " ");
                }
                n -= (k - 3);
                if(n % 2 != 0) {
                    out.print(1 + " " + ((n - 1) / 2) + " " + ((n - 1) / 2));
                }
                else {
                    if(n % 4 == 0) {
                        out.print((n / 4) + " " + (n / 4) + " " + (n / 2));
                    }
                    else {
                        out.print(2 + " " + (n / 2 - 1) + " " + (n / 2 - 1));
                    }
                }
            }
            out.println();
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
