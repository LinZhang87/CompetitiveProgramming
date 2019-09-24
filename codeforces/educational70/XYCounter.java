package codeforces.educational70;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/7/19
 */

public class XYCounter {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String s = in.next();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                out.println(minDigitsInsertion(s, i, j));
            }
        }
        out.close();
    }
    private static int minDigitsInsertion(String s, int x, int y) {
        String correct_x = "";
        String correct_y = "";

        return 0;
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
