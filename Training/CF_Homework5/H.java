package practices.homework5;

import java.io.*;
import java.util.*;

/*
    Key idea:
    1. if p[i] - p[i + 1] > 1, it takes 0 cost to transit into the situation where the current standing height is p[i + 1] + 1
       and the next platform is at height p[i + 1].
    2. if p[i] - p[i + 1] == 1, this is also the same with the transitioned situation in case 1. In this situation, we need to
       check the next platform at height p[i + 2] if exists to determine if it is necessary to incur a cost of 1.

       if p[i + 2] exists
       (a). the current standing height - p[i + 2] > 2, then we must spend cost of 1. The new standing height
       is the current standing height - 2. And p[i + 2] becomes the next platform to compare with.

       (b). the current standing height - p[i + 2] <= 2, then we can safely drop to p[i + 2], p[i + 2] becomes the new standing height
       and p[i + 3] if exists becomes the next platform to compare with.

       The idea is fairly straightforward, you just need to think it through before jumping into coding to avoid bugs!
 */

public class H {
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
            int h = in.nextInt(), n = in.nextInt();
            int[] p = new int[n + 1];
            for(int i = 0; i < n; i++) p[i] = in.nextInt();
            int ans = 0, currH = h, nextP = 1;
            while(currH > 2) {
                if(currH - p[nextP] == 1) {
                    if(nextP + 1 <= n) {
                        if(currH - p[nextP + 1] > 2) {
                            ans++;
                            currH -= 2;
                            nextP++;
                        }
                        else {
                            currH = p[nextP + 1];
                            nextP += 2;
                        }
                    }
                }
                else {
                    currH = p[nextP] + 1;
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