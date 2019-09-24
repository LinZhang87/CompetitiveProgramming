//package codeforces.round577;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/4/19
 */

public class MaximumMedian {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
//        int n = 7, k = 7;
//        int[] a = {4, 1, 2, 4, 3, 4, 4};

        long ans = a[n / 2];
        Arrays.sort(a);
        int left = n / 2;
        int right = n / 2 + 1;
        while(right < n && ((long)a[right]) == ans) {
            right++;
        }
        while(k >= (right - left)) {
            k -= (right - left);
            ans++;
            while(right < n && ((long)a[right]) == ans) {
                right++;
            }
        }
        out.print(ans);
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
