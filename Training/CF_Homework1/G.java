package tutoring;

import java.io.*;
import java.util.*;

public class G {
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
            long[] c = in.nextLongArrayPrimitive(n);
            long[] psEven = new long[n], psOdd = new long[n];
            psEven[0] = c[0];
            for(int i = 1; i < n; i++) {
                if(i % 2 == 0) {
                    psEven[i] = psEven[i - 1] + c[i];
                    psOdd[i] = psOdd[i - 1];
                }
                else {
                    psEven[i] = psEven[i - 1];
                    psOdd[i] = psOdd[i - 1] + c[i];
                }
            }
            long currMin1 = c[0], currMin2 = c[1], sum1 = currMin1 * n, sum2 = currMin2 * n, ans = sum1 + sum2;
            //k is the number of used segments
            for(int k = 3; k <= n; k++) {
                int len = (k + 1) / 2;
                if(k % 2 == 0) {
                    if(c[k - 1] < currMin2) {
                        //update
                        sum2 = psOdd[k - 1] + c[k - 1] * (n - len);
                        currMin2 = c[k - 1];
                    }
                    else {
                        sum2 += c[k - 1] - currMin2;
                    }
                }
                else {
                    if(c[k - 1] < currMin1) {
                        //update
                        sum1 = psEven[k - 1] + c[k - 1] * (n - len);
                        currMin1 = c[k - 1];
                    }
                    else {
                        sum1 += c[k - 1] - currMin1;
                    }
                }
                ans = Math.min(ans, sum1 + sum2);
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


