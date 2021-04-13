package tutoring.homework2;

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
            int[] a = in.nextIntArrayPrimitive(n);
            //map1: key: number; value: its frequency
            Map<Integer, Integer> map1 = new HashMap<>();
            for(int v : a) map1.put(v, map1.getOrDefault(v, 0) + 1);
            //map2: key: a frequency; value: the count of different numbers that have this same frequency
            Map<Integer, Integer> map2 = new HashMap<>();
            for(Map.Entry<Integer, Integer> e : map1.entrySet()) {
                map2.put(e.getValue(), map2.getOrDefault(e.getValue(), 0) + 1);
            }
            int ans = n;
            List<int[]> list = new ArrayList<>();
            for(Map.Entry<Integer, Integer> e : map2.entrySet()) {
                //key is a freq number; value is the count of different numbers that has this freq
                list.add(new int[]{e.getKey(), e.getValue()});
            }
            Collections.sort(list, Comparator.comparingInt(b->b[0]));
            int[] ps = new int[list.size()];
            int[] ps1 = new int[list.size()];
            ps[0] = list.get(0)[1];
            ps1[0] = list.get(0)[1] * list.get(0)[0];
            for(int i = 1; i < ps.length; i++) {
                ps[i] = ps[i - 1] + list.get(i)[1];
                ps1[i] = ps1[i - 1] + list.get(i)[1] * list.get(i)[0];
            }
            int right = 0;
            for(int i = ps.length - 1; i >= 0; i--) {
                int left = (i > 0 ? ps1[i - 1] : 0);
                right += (ps[ps.length - 1] - ps[i]) * (i < ps.length - 1 ? (list.get(i + 1)[0] - list.get(i)[0]) : 0);
                int sum = left + right;
                ans = Math.min(ans, sum);
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


