package tutoring;

import java.io.*;
import java.util.*;

public class E {
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
            int m = 0, c = -1;
            if(n > 2) {
                int diff = a[1] - a[0], i = 2;
                boolean constantDiff = true;
                for(; i < n; i++) {
                    if(a[i] - a[i - 1] != diff) {
                        constantDiff = false;
                        break;
                    }
                }
                if(constantDiff) {
                    m = 0;
                }
                else {
                    //a[i - 2], a[i - 1], a[i]
                    if(a[i - 2] <= a[i - 1] && a[i - 1] <= a[i] || a[i - 2] >= a[i - 1] && a[i - 1] >= a[i]) {
                        m = -1;
                    }
                    if(a[i] < a[i - 1]) {
                        c = a[i - 1] - a[i - 2];
                        m = c + a[i - 1] - a[i];
                    }
                    else {
                        c = a[i] - a[i - 1];
                        m = c + a[i - 2] - a[i - 1];
                    }
                    for(int j = 0; j < n; j++) {
                        if(a[j] >= m || j > 0 && a[j] != (a[j - 1] + c) % m) {
                            m = -1;
                            break;
                        }
                    }
                }
            }
            out.print(m + " ");
            if(m > 0) {
                out.print(c);
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
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }
}


