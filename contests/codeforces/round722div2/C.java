package codeforces.round722div2;

import java.io.*;
import java.util.*;

/*
    Greedy approach like fixing root node's number to be either left or right end is incorrect.

    Key observation:
    for a parent node and a child node, we have [lp, rp] and [lc, rc], it is easy to show
    by drawing that no matter what the relative position of these 2 ranges, it is always optimal
    to have the parent node pick either lp or rp.

    So for each node V we should compute and return to its parent node the following 2 things:
    1. the max sum of the entire subtree rooted at V if V picks lv.
    2. the max sum of the entire subtree rotted at V if V picks rv.

    To do this, we recursively compute the information of the above 2 for all V's child nodes and use them
    to compute V's result.

    The dfs method is self explanatory.
 */
public class C {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }
    static int n;
    static List<Integer>[] adj;
    static long[] l, r;
    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            n = in.nextInt();
            adj = new List[n + 1];
            for(int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            l = new long[n + 1];
            r = new long[n + 1];
            for(int i = 1; i <= n; i++) {
                l[i] = in.nextInt();
                r[i] = in.nextInt();
            }
            for(int i = 0; i < n - 1; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            long[] ans = dfs(1, 0);
            out.println(Math.max(ans[0], ans[1]));
        }
        out.close();
    }

    static long[] dfs(int curr, int par) {
        long[] ans = new long[2];
        for(int next : adj[curr]) {
            if(next == par) continue;
            long[] nextAns = dfs(next, curr);
            ans[0] += Math.max(nextAns[0] + Math.abs(l[curr] - l[next]), nextAns[1] + Math.abs(l[curr] - r[next]));
            ans[1] += Math.max(nextAns[0] + Math.abs(r[curr] - l[next]), nextAns[1] + Math.abs(r[curr] - r[next]));
        }
        return ans;
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