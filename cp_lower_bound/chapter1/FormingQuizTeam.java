package chapter1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 6/17/19
 */

/*
UVa 10911
 */

public class FormingQuizTeam {
    private static Map<Integer, Double> res = new HashMap<>();

    public static void main(String[] args) {
        FormingQuizTeam.FastScanner in = new FormingQuizTeam.FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

//        try {
//            Main.FastScanner in = new Main.FastScanner(new FileInputStream("src/input.in"));
//            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));

            String line;
            int caseNum = 1;
            while((line = in.nextLine()) != null) {
                int n = Integer.parseInt(line);
                if(n == 0) {
                    break;
                }
                int[][] p = new int[n * 2][2];
                for(int i = 0; i < n * 2; i++) {
                    in.next();
                    p[i][0] = in.nextInt();
                    p[i][1] = in.nextInt();
                }
                out.println("Case " + caseNum + ": " + String.format("%.2f", minDistanceSum(p)));
                caseNum++;
            }
            out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    private static double minDistanceSumDp(int[][] p) {
//
//    }

    private static double minDistanceSum(int[][] p) {
        res = new HashMap<>();
        return dfs(p, 0);
    }

    private static double dfs(int[][] p, int state) {
        if((int)Math.pow(2, p.length) == state + 1) {
            return 0.0;
        }
        if(res.containsKey(state)) {
            return res.get(state);
        }
        double minSum = Double.MAX_VALUE;
//        for(int i = 0; i < p.length; i++) {
////            if((state & (1 << i)) != 0) {
////                continue;
////            }
////            for(int j = 0; j < p.length; j++) {
////                if(j == i || (state & (1 << j)) != 0) {
////                    continue;
////                }
////                int x = p[i][0] - p[j][0];
////                int y = p[i][1] - p[j][1];
////                int mask = ((1 << i) | (1 << j));
////                minSum = Math.min(minSum, Math.sqrt(x * x + y * y) + dfs(p, state | mask));
////            }
////        }

        int i, j;
       for(i = 0; i < p.length; i++) {
           if((state & (1 << i)) == 0) {
               break;
           }
       }
       for(j = i + 1; j < p.length; j++) {
           if((state & (1 << j)) == 0) {
               int x = p[i][0] - p[j][0];
               int y = p[i][1] - p[j][1];
               int mask = ((1 << i) | (1 << j));
               minSum = Math.min(minSum, Math.hypot(p[i][0] - p[j][0],  p[i][1] - p[j][1]) + dfs(p, state | mask));
           }
        }
        res.put(state, minSum);
        return minSum;
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
