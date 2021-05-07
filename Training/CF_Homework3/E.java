package practices.homework3;

import java.io.*;
import java.util.*;

/*
    Important observation: if i is not coprime with n, i.e, gcd(i, n) > 1, then i can not be used.
    Proof: if i is used, and we denote gcd(i, n) as x, x > 1. Then the product is k1 * x and n is k2 * x.
    product % n will be one of these {0, x, x * 2, x * 3, ......, x * c} x * c <= n - 1. This means product % n can
    never be 1 if such i is used.

    Solution:
    1. find all coprime with n numbers from 1 to n - 1.
    2. get the product of all these coprime numbers modulo n, call it p. if p is 1, then we use all these numbers; else p > 1,
       we just need to get rid of number p. p must be one of the coprime numbers.

       Claim: p must be one of these coprime numbers with n.
       Proof: Say we have 3 coprime numbers v1, v2 and v3. then v1 * v2 * v3 is also coprime with n;
              (v1 * v2 * v3) % n = p.
              v1 * v2 * v3 is coprime with n since each number is coprime with n.
              v1 * v2 * v3 = c * n + p; if p is not coprime with n, then v1 * v2 * v3 is not coprime with n, contradiction!
              
 */

public class E {
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
            int n = in.nextInt(), cnt = 0;
            long p = 1;
            boolean[] include = new boolean[n];
            for(int i = 1; i < n; i++) {
                if(gcd(i, n) == 1) {
                    include[i] = true;
                    p = p * i % n;
                    cnt++;
                }
            }
            if(p != 1) {
                include[(int)p] = false;
                cnt--;
            }
            out.println(cnt);
            for(int i = 1; i < n; i++) {
                if(include[i]) out.print(i + " ");
            }
            out.println();
        }
        out.close();
    }

    static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
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
