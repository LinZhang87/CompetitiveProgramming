package practices.homework3;

import java.io.*;
import java.util.*;

/*
    Important observations: (Xi, Yi) increases by at least a factor of ax with ax >= 2, this means within the Long.MAX_VALUE range,
    there are at most O(log(Long.MAX_VALUE)) nodes to consider. 
    
    Incorrect attempt: 
    1. Find the node (Xi, Yi) that is closest to (Xs, Ys), i.e, |Xs - Xi| + |Ys - Yi| is minimum. 
    2. Then try to collect the previous (i - 1)th node if possible as it is the closest to the ith node.
    3. Repeat step 2 going backward until we run out of time
    
    Counter example: It is possible that the distance between the ith node (Xi, Yi) and the (i - 1)th node is pretty big, call it D1.
    Going to the ith node first versus going to the (i - 1)th node first saves us some distance, call it D2.
    It is possible that D1 is much bigger than D2 such that we spend a lot of time travelling from ith node to (i - 1)th node, resulting
    a much smaller remaining time to collect smaller nodes. In this case, it is better that if we skip the ith node and go directly to the 
    (i - 1)th node and start to collect all smaller nodes from there. 
    
    Correct solution:
    Since we only have O(log(Long.MAX_VALUE)) nodes, we can just iterate all possible [L, R] ranges and compute the nodes we can collect.
    For a fixed range [L, R], we only need to consider either go to the Lth node first and collect all nodes from L to R or go to the Rth node first
    and collect all nodes from R to L. All other first visiting node choices yield a longer time, this is easy to see if you draw a diagram.
    
    The total runtime is O(log(Long.MAX_VALUE) ^ 3).
 */

public class I {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        //solve(in.nextInt());
        solve(1);
    }

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            long x0 = in.nextLong(), y0 = in.nextLong(), ax = in.nextLong(), ay = in.nextLong(), bx = in.nextLong(), by = in.nextLong();
            long xs = in.nextLong(), ys = in.nextLong(), t = in.nextLong();
            List<long[]> list = new ArrayList<>();
            long currX = x0, currY = y0;
            while(Math.abs(xs - currX) + Math.abs(ys - currY) > t) {
                if((Long.MAX_VALUE - bx) / ax < currX || (Long.MAX_VALUE - by) / ay < currY) {
                    break;
                }
                currX = ax * currX + bx;
                currY = ay * currY + by;
            }

            while((Long.MAX_VALUE - bx) / ax >= currX && (Long.MAX_VALUE - by) / ay >= currY
                    && Math.abs(xs - currX) + Math.abs(ys - currY) <= t) {
                list.add(new long[]{currX, currY});
                currX = ax * currX + bx;
                currY = ay * currY + by;
            }
            int ans = 0;
            for(int l = 0; l < list.size(); l++) {
                for(int r = l; r < list.size(); r++) {
                    int cnt1 = 1, cnt2 = 1;
                    long t1 = t - Math.abs(xs - list.get(l)[0]) - Math.abs(ys - list.get(l)[1]);
                    long t2 = t - Math.abs(xs - list.get(r)[0]) - Math.abs(ys - list.get(r)[1]);
                    for(int k = l; k < r && t1 > 0; k++) {
                        t1 -= (Math.abs(list.get(k)[0] - list.get(k + 1)[0]) + Math.abs(list.get(k)[1] - list.get(k + 1)[1]));
                        if(t1 >= 0) cnt1++;
                    }
                    for(int k = r; k > l && t2 > 0; k--) {
                        t2 -= (Math.abs(list.get(k)[0] - list.get(k - 1)[0]) + Math.abs(list.get(k)[1] - list.get(k - 1)[1]));
                        if(t2 >= 0) cnt2++;
                    }
                    ans = Math.max(ans, Math.max(cnt1, cnt2));
                }
            }
            out.println(ans);
        }
        out.close();
    }

    static void initReaderPrinter(boolean test) {
        if (test) {
            try {
                in = new InputReader(new FileInputStream("src/input.in"));
                out = new PrintWriter(new FileOutputStream("src/output.out"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            in = new InputReader(System.in);
            out = new PrintWriter(System.out);
        }
    }

    static class InputReader {
        BufferedReader br;
        StringTokenizer st;

        InputReader(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream), 32768);
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        Integer[] nextIntArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextIntArrayPrimitiveOneIndexed(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextInt();
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextLongArrayPrimitiveOneIndexed(int n) {
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) a[i] = nextLong();
            return a;
        }

        String[] nextStringArray(int n) {
            String[] g = new String[n];
            for (int i = 0; i < n; i++) g[i] = next();
            return g;
        }
    }
}
