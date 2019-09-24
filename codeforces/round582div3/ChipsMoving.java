package codeforces.round582div3;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/30/19
 */

public class ChipsMoving {
    public static void main(String[] args) {
//        try {
//            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
//            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
            FastScanner in = new FastScanner(System.in);
            PrintWriter out = new PrintWriter(System.out);
            int n;
            n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int odd = 0, even = 0;
            for(int i = 0; i < n; i++) {
                if(a[i] % 2 != 0) {
                    even++;
                }
                else {
                    odd++;
                }
            }
            out.print(Math.min(odd, even));
            out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
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
