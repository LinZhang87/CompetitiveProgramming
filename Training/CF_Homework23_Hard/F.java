package practices.homework23_hard;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

/*
   Find out each non-intersecting connected cities: in each such connected city groups, if there are C cities, then
   depending on the starting direction, either a city gets 0 or C visited count.

   For all cities that only have in-coming edges, the answer is 1 because we can always visit the start city!
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
            char[] c = in.next().toCharArray();
            boolean[] processed = new boolean[n];
            int[] ans = new int[n + 1];
            for(int i = 0; i < n; i++) {
                if(processed[i]) continue;
                List<Integer> visit = new ArrayList<>();
                visit.add(i);
                processed[i] = true;
                int l = i - 1;
                while(l >= 0 && c[l] != c[l + 1]) {
                    processed[l] = true;
                    visit.add(l);
                    l--;
                }
                int r = i + 1;
                while(r < n && c[r] != c[r - 1]) {
                    processed[r] = true;
                    visit.add(r);
                    r++;
                }
                for(int x : visit) {
                    if(c[x] == 'L') {
                        ans[x + 1] = r - l;
                    }
                    else {
                        ans[x] = r - l;
                    }
                }
            }
            for(int i = 0; i <= n; i++) {
                if(ans[i] == 0) ans[i] = 1;
            }
            for(int v : ans) out.print(v + " ");
            out.println();
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
