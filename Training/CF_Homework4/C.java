package practices.homework4;

import java.io.*;
import java.util.*;

/*
    if n <= 12, then convert [3, n] to 1 using 1 cost for each, by dividing n; then convert n to 1 by keep dividing 2, using at most 4 cost;
    if n > 12, convert [3, n - 1] excluding 12 to 1 using 1 cost for each, by dividing n; then convert n to 1 by keep dividing 12.
    Lastly, convert 12 to 1 by dividing 2 using 4 cost.

    12^5 > 2 * 10^5, so the cost is at most n - 4 + 4 + 5 <= n + 5.
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
            List<Integer> lx = new ArrayList<>(), ly = new ArrayList<>();
            for(int i = 3; i <= n - 1; i++) {
                if(i == 12) continue;
                lx.add(i);
                ly.add(n);
            }
            int v = n;
            if(n > 12) {
                while(v > 1) {
                    lx.add(n);
                    ly.add(12);
                    v = v / 12 + (v % 12 == 0 ? 0 : 1);
                }
            }
            n = Math.min(n, 12);
            v = n;
            while(v > 1) {
                lx.add(n);
                ly.add(2);
                v = v / 2 + (v % 2 == 0 ? 0 : 1);
            }
            out.println(lx.size());
            for(int i = 0; i < lx.size(); i++) {
                out.println(lx.get(i) + " " + ly.get(i));
            }
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
