package codeforces.round722div2;

import java.io.*;
import java.util.*;

/*
    case 1. all numbers in A <= 0, given |ai - aj| >= 0 > max(A), we can take the entire A
    case 2. There are some A[i] > 0. By definition, we can take at most 1 positive number because the absolute difference
    between two positive numbers is always smaller than max(A).
    
    If we are allowed to take 1 positive integer, by greedy principle, we should take A[i] that is the smallest among all
    positive numbers.

    To check if we can take the smallest positive number, we first sort A. Then we just need to check the following:
    for all A[i] <= 0, if any absolute difference between two adjacent numbers < the smallest positive number.
    If true, we can not take the smallest positive number and can only take all A[i] <= 0;
    If false, we can take the smallest positive number plus all A[i] <= 0;
 */

public class B {
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
            Integer[] a = in.nextIntArray(n);
            Arrays.sort(a);
            if(a[n - 1] <= 0) {
                out.println(n);
            }
            else {
                int i = n - 1;
                for(; i >= 0 && a[i] > 0; i--) {}
                boolean pick = true;
                for(int j = i; j > 0; j--) {
                    if(a[j] - a[j - 1] < a[i + 1]) {
                        pick = false;
                        break;
                    }
                }
                int ans = pick ? i + 2 : i + 1;
                out.println(ans);
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