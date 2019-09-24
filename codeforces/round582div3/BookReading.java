package codeforces.round582div3;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/30/19
 */

public class BookReading {
    public static void main(String[] args) {
        try {
            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
//        FastScanner in = new FastScanner(System.in);
//        PrintWriter out = new PrintWriter(System.out);
            int q = in.nextInt();
            while(q > 0) {
                BigInteger n = new BigInteger(in.next());
                BigInteger m = new BigInteger(in.next());
                out.print(getSum(n, m));
                q--;
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static long getSum(BigInteger n, BigInteger m) {
        long sum = 0;

        return sum;
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
