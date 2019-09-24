package codeforces.round582div3;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/30/19
 */

public class BadPrices {
    public static void main(String[] args) {
//        try {
//            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
//            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t;
        t = in.nextInt();

        while(t > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            out.println(getNumOfBadPricesLinear(a));
            t--;
        }
        out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private static int getNumOfBadPrices(int[] a) {
        int count = 0;
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int v : a) {
            while(maxPq.size() > 0 && maxPq.peek() > v) {
                count++;
                maxPq.poll();
            }
            maxPq.add(v);
        }
        return count;
    }

    private static int getNumOfBadPricesLinear(int[] a) {
        int count = 0, n = a.length;
        int[] minPrices = new int[n];
        minPrices[n - 1] = a[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            minPrices[i] = Math.min(a[i], minPrices[i + 1]);
        }
        for(int i = 0; i < n; i++) {
            if(a[i] > minPrices[i]) {
                count++;
            }
        }
        return count;
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
