package codeforces.educational70;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/7/19
 */

public class ReverseBinaryString {

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();
        String[][] str = new String[t][2];
        for(int i = 0; i < t; i++) {
            str[i][0] = in.next();
            str[i][1] = in.next();
        }
        for(int i = 0; i < t; i++) {
            out.println(minK(str[i][0], str[i][1]));
        }
        out.close();
    }
    private static int minK(String s1, String s2) {
        int k = 0,i1 = s1.length() - 1,  offset = 0;
        while(s2.charAt(s2.length() - 1 - offset) == '0') {
            offset++;
        }
        i1 -= offset;
        while(i1 >= 0) {
            if(s1.charAt(i1) == '1') {
                break;
            }
            else {
                k++;
                i1--;
            }
        }
        return k;
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

