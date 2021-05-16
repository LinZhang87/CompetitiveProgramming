package practices.homework4;

import java.io.*;
import java.util.*;

/*
    Key idea: if we sort heroes based on their powers in decreasing order and we keep a running monster max power, it
    takes O(M) time to do binary search and find all the heroes that meet the power threshold. In these heroes, we only
    care about if there is at least 1 hero whose endurance allows defeating the current monster on the same day. If this
    is true, we don't need to start a new day. Otherwise we must start a new day and reset running monster max power and
    the count of defeated monsters on the same day.

    This calls for a prefix max preprocessing!

    1. Sort heroes based on their powers in decreasing order;
    2. Create a prefix max array using the hero array after step 1.
    3. iterate all monsters from left to right, find all heroes [0, j] such that their powers >= current max monster power.
    4. if j < 0, return -1; else check if prefix max [j] > defeated monster count of that day, if true, we can defeat the
       current monster on the same day; otherwise we must start a new day.
 */

public class F {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        //initReaderPrinter(true);
        initReaderPrinter(false);
        solve(in.nextInt());
        //solve(1);        
    }

    static void solve(int testCnt) {
        for (int testNumber = 0; testNumber < testCnt; testNumber++) {
            int n = in.nextInt();
            int[] monster = in.nextIntArrayPrimitive(n);
            int m = in.nextInt();
            int[][] hero = new int[m][2];
            for(int i = 0; i < m; i++) {
                hero[i][0] = in.nextInt();
                hero[i][1] = in.nextInt();
            }
            Arrays.sort(hero, Comparator.comparingInt(e->-e[0]));
            int[] prefixMax = new int[m];
            prefixMax[0] = hero[0][1];
            for(int i = 1; i < m; i++) {
                prefixMax[i] = Math.max(prefixMax[i - 1], hero[i][1]);
            }
            int ans = 1, idx = 0, maxPower = 0, cnt = 0;
            while(idx < n) {
                maxPower = Math.max(maxPower, monster[idx]);
                int j = bs(hero, maxPower);
                if(j < 0) break;
                if(prefixMax[j] > cnt) {
                    cnt++;
                    idx++;
                }
                else {
                    maxPower = 0;
                    cnt = 0;
                    ans++;
                }
            }
            out.println(idx < n ? -1 : ans);
        }
        out.close();
    }

    static int bs(int[][] hero, int t) {
        int l = 0, r = hero.length - 1;
        while(l < r - 1) {
            int mid = l + (r - l) / 2;
            if(hero[mid][0] >= t) {
                l = mid;
            }
            else {
                r = mid - 1;
            }
        }
        if(hero[r][0] >= t) return r;
        else if(hero[l][0] >= t) return l;
        return -1;
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
