package codeforces.round582div3;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 9/4/19
 */


public class UnstableStringSort {
    public static void main(String[] args) {
        try {
            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
//        FastScanner in = new FastScanner(System.in);
//        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = new int[n];
        int[] q = new int[n];
        for(int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++) {
            q[i] = in.nextInt();
        }
        out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
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

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble() { return Double.parseDouble(next()); }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
