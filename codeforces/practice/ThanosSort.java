package codeforces.practice;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 9/4/19
 */

public class ThanosSort {
    public static void main(String[] args) {
//        try {
//            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
//            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.print(thanosSortRecursion(a, 0, a.length - 1));
        out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private static int thanosSortRecursion(int[] a, int left, int right) {
        if(left == right) {
            return 1;
        }
        int mid = left + (right - left) / 2;
        boolean ls = true , rs = true;
        for(int i = left + 1; i <= mid; i++) {
            if(a[i] < a[i - 1]) {
                ls = false;
                break;
            }
        }
        for(int i = mid + 2; i <= right; i++) {
            if(a[i] < a[i - 1]) {
                rs = false;
                break;
            }
        }
        if(ls && rs && a[mid] <= a[mid + 1]) {
            return right - left + 1;
        }
        else if(ls || rs) {
            return (right - left + 1) / 2;
        }
        return Math.max(thanosSortRecursion(a, left, mid), thanosSortRecursion(a, mid + 1, right));
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
