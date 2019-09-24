package codeforces.round577;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/4/19
 */

public class TreasureHunting {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[][] treasures = new int[k][2];
        for(int i = 0; i < k; i++) {
            treasures[i][0] = in.nextInt();
            treasures[i][1] = in.nextInt();
        }
        int[] safe = new int[q];
        for(int i = 0; i < q; i++) {
            safe[i] = in.nextInt();
        }

        long minSteps = 0;

        out.print(minSteps);
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
