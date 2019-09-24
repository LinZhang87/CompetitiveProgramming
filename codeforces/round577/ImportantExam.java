package codeforces.round577;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/4/19
 */

public class ImportantExam {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        String[] answers = new String[n];
        int[] solution = new int[m];

        int maxScore = 0;

        for(int i = 0; i < n; i++) {
            answers[i] = in.next();
        }
        for(int i = 0; i < m; i++) {
            solution[i] = in.nextInt();
        }
        for(int i = 0; i < m; i++) {
            int[] count = new int[5];
            for(int j = 0; j < n; j++) {
                count[answers[j].charAt(i) - 'A']++;
            }
            int max = count[0];
            for(int k = 1; k < 5; k++) {
                max = Math.max(max, count[k]);
            }
            maxScore += max * solution[i];
        }
        out.print(maxScore);
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