package tutoring;

import java.io.*;
import java.util.*;

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
            int n = in.nextInt(), m = in.nextInt();
            int[][] f = new int[m][];
            for(int i = 0; i < m; i++) {
                int j = in.nextInt();
                f[i] = in.nextIntArrayPrimitive(j);
            }
            boolean possible = true;
            int[] pickCnt = new int[n + 1];
            int[] ans = new int[m];
            for(int i = 0; i < m; i++) {
                if(f[i].length == 1) {
                    pickCnt[f[i][0]]++;
                    if(pickCnt[f[i][0]] > (m + 1) / 2) {
                        possible = false;
                        break;
                    }
                    ans[i] = f[i][0];
                }
            }
            if(possible) {
                for(int i = 0; i < m; i++) {
                    if(ans[i] == 0) {
                        int cnt = (m + 1) / 2;
                        int pickForDayI = 0;
                        for(int v : f[i]) {
                            if(pickCnt[v] < cnt) {
                                cnt = pickCnt[v];
                                pickForDayI = v;
                            }
                        }
                        if(cnt == (m + 1) / 2) {
                            possible = false;
                            break;
                        }
                        ans[i] = pickForDayI;
                        pickCnt[pickForDayI]++;
                    }
                }
            }
            if(!possible) out.println("NO");
            else {
                out.println("YES");
                for(int v : ans) {
                    out.print(v + " ");
                }
                out.println();
            }
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
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        int[] nextIntArrayPrimitive(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        Long[] nextLongArray(int n) {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        long[] nextLongArrayPrimitive(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }
}


