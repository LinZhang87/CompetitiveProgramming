package codeforces.educational70;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/7/19
 */

public class WASDRobot {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();
        String[] seq = new String[t];
        for(int i = 0; i < t; i++) {
            seq[i] = in.next();
        }
        for(int i = 0; i < t; i++) {
            out.println(minGrid(seq[i]));
        }
        out.close();
    }
    private static long minGrid(String s) {
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
