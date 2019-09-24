package chapter1;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 6/17/19
 */

class GreedyGiftGivers {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String line;
        int testCase = 0;
        while((line = in.nextLine()) != null) {
            int n = Integer.parseInt(line);
            Map<String, Integer> map = new LinkedHashMap<>();
            for(int i = 0; i < n; i++) {
                map.put(in.next(), 0);
            }
            for(int i = 0; i < n; i++) {
                line = in.nextLine();
                String[] strs = line.split(" ");
                String giver = strs[0];
                int total = Integer.parseInt(strs[1]);
                int count = Integer.parseInt(strs[2]);
                int giveAmount = count == 0 ? 0 : total - total % count;
                map.put(giver, map.get(giver) - giveAmount);
                int j = 3;
                while(j < strs.length) {
                    map.put(strs[j], map.get(strs[j]) + total / count);
                    j++;
                }
            }

            //print a blank line in between test cases
            if(testCase > 0) {
                out.println();
            }

            for(String name : map.keySet()) {
                out.println(name + " " + map.get(name));
            }
            testCase++;
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
