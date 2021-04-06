//package tutoring;

import java.io.*;
import java.util.*;

public class A {
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
            int n = in.nextInt(), width = in.nextInt();
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            for(int i = 0; i < n; i++) {
                int w = in.nextInt();
                tm.put(w, tm.getOrDefault(w, 0) + 1);
            }
            int ans = 1, levelWidth = width;
            while (tm.size() > 0) {
                if(levelWidth < tm.firstKey()) {
                    ans++;
                    levelWidth = width;
                }
                else {
                    Map.Entry<Integer, Integer> e = tm.floorEntry(levelWidth);
                    levelWidth -= e.getKey();
                    if(e.getValue().compareTo(1) == 0) {
                        tm.remove(e.getKey());
                    }
                    else {
                        tm.put(e.getKey(), e.getValue() - 1);
                    }
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

 
