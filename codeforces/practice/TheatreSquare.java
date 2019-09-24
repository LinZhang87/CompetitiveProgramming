package codeforces.practice;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 7/28/19
 */

public class TheatreSquare {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long n, m, a;
        n = in.nextLong();
        m = in.nextLong();
        a = in.nextLong();

        long rowCount = m / a + (m % a != 0 ? 1 : 0);
        long colCount = n / a + (n % a != 0 ? 1 : 0);

        out.print(rowCount * colCount);
        out.close();

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
