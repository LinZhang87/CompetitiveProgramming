package tutoring;

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

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            int[] q = in.nextIntArrayPrimitive(n);
            boolean[] used = new boolean[n + 1];
            int[] minP = new int[n], maxP = new int[n];
            for(int i = 0; i < n; i++) {
                if(!used[q[i]]) {
                    minP[i] = q[i];
                    maxP[i] = q[i];
                    used[q[i]] = true;
                }
            }
            //fill in minP. For an unfilled position i, get the min unused number that is < the prev fixed number
            int v = 1;
            for(int i = 0; i < n; i++) {
                if(minP[i] == 0) {
                    while(v <= n && used[v]) v++;
                    minP[i] = v;
                    v++;
                }
            }
            //fill in maxP. For an unfilled position i, get the max unused number that is < min prev fixed
            TreeSet<Integer> ts = new TreeSet<>();
            for(int i = 1; i <= n; i++) {
                if(!used[i]) ts.add(i);
            }
            int prevFixed = 0;
            for(int i = 0; i < n; i++) {
                if(maxP[i] > 0) {
                    prevFixed = maxP[i];
                }
                else {
                    while(v >= 1 && used[v]) v--;
                    int d = ts.lower(prevFixed);
                    maxP[i] = d;
                    ts.remove(d);
                }
            }
            for(int d : minP) {
                out.print(d + " ");
            }
            out.println();
            for(int d : maxP) {
                out.print(d + " ");
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


