package codeforces.round577;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/4/19
 */

public class ZeroArray {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n;
        n = in.nextInt();
        int[] a = new int[n];
        long sum = 0;
        long max = 0;
        for(int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
            max = Math.max(max, a[i]);
        }
        if(sum % 2 != 0) {
            out.print("NO");
        }
        else {
            if(max * 2 > sum) {
                out.print("NO");
            }
            else {
                out.print("YES");
            }
        }
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
