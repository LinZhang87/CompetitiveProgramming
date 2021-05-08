package practices.homework2;

import java.io.*;
import java.util.*;

public class D {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static int[] map = {0,1,5,-1,-1,2,-1,-1,8,-1};
    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int h = in.nextInt(), m = in.nextInt();
            String s = in.next();
            int sh = Integer.parseInt(s.substring(0,2)), sm = Integer.parseInt(s.substring(3));
            char[] ans = new char[5];
            ans[2] = ':';
            for(int et = 0; et < h * m; et++) {
                int eh = et / m;
                int em = et % m;
                int m1 = (sm + em) % m, h1 = (sh + eh + (sm + em) / m) % h;
                if(check(h, m, h1, m1)) {
                    ans[0] = (char)('0' + (h1 / 10));
                    ans[1] = (char)('0' + (h1 % 10));
                    ans[3] = (char)('0' + (m1 / 10));
                    ans[4] = (char)('0' + (m1 % 10));
                    break;
                }
            }
            out.println(String.valueOf(ans));
        }
        out.close();
    }

    static boolean check(int h, int m, int h1, int m1) {
        if(map[h1 / 10] < 0 || map[h1 % 10] < 0 || map[m1 / 10] < 0 || map[m1 % 10] < 0) {
            return false;
        }
        int h2 = map[m1 % 10] * 10 + map[m1 / 10], m2 = map[h1 % 10] * 10 + map[h1 / 10];
        return h2 < h && m2 < m;
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
