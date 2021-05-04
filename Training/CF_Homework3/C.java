package practices.homework3;

import java.io.*;
import java.util.*;

/*
    We only need to consider 2 cases: 1. 2 numbers left; 2. 3 numbers left;
    Using the fact x ^ x = 0 and 0 ^ x = x,
    bigger even count can be reduced to 2 numbers;
    bigger odd count can be reduced to 3 numbers;

    So we just need to partition A into either 2 or 3 sub-arrays and check if their xor are the same.
    To check a sub-array's xor in O(1) time, we can combine the concept of prefix sum and the following property:

    for i < j, xor[0, j] = xor[0, i - 1] ^ xor[i, j],  xor[i, j] = xor[0, j] ^ xor[0, i - 1]

    Precompute a xor array using the above properties.
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

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            int[] a = in.nextIntArrayPrimitive(n);
            int[] xor = new int[n];
            xor[0] = a[0];
            for(int i = 1; i < n; i++) {
                xor[i] = xor[i - 1] ^ a[i];
            }
            boolean ans = false;
            for(int i = 0; i <= n - 2; i++) {
                if(xor[i] == (xor[n - 1] ^ xor[i])) {
                    ans = true;
                    break;
                }
            }
            if(!ans) {
                for(int i = 0; i <= n - 3; i++) {
                    for(int j = i + 1; j <= n - 2; j++) {
                        if(xor[i] == (xor[j] ^ xor[i]) && xor[i] == (xor[n - 1] ^ xor[j])) {
                            ans = true;
                            break;
                        }
                    }
                    if(ans) break;
                }
            }
            out.println(ans ? "YES" : "NO");
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
