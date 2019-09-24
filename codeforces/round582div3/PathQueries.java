package codeforces.round582div3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 8/30/19
 */

public class PathQueries {
    public static void main(String[] args) {
//        try {
//            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
//            PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[n - 1][3];
        for(int i = 0; i < n - 1; i++) {
            edges[i] = new int[3];
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
            edges[i][2] = in.nextInt();
        }
        int[][] queries = new int[m][2];
        for(int i = 0; i < m; i++) {
            queries[i] = new int[2];
            queries[i][0] = in.nextInt();
            queries[i][1] = i;
        }
        Arrays.sort(edges, (e1, e2) -> {return e1[2] - e2[2]; });
        Arrays.sort(queries, (q1, q2) -> {return q1[0] - q2[0]; });
        long[] res = pathQuery(edges, n , queries);
        for(int i = 0; i < m; i++) {
            out.print(res[i] + " ");
        }
        out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private static long[] pathQuery(int[][] edges, int n, int[][] maxWeights) {
        long sum = 0;
        long[] res = new long[maxWeights.length];
        UnionFind uf = new UnionFind(n);
        int j = 0;
        for(int i = 0; i < maxWeights.length; i++) {
            while (j < edges.length && edges[j][2] <= maxWeights[i][0]) {
                sum += uf.union(edges[j][0] - 1, edges[j][1] - 1);
                j++;
            }
            res[maxWeights[i][1]] = sum;
        }
        return res;
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

class UnionFind {
    protected int[] id;   //parent link
    protected long[] sz;  //size of component for roots

    public UnionFind(int N) {
        this.id = new int[N];
        this.sz = new long[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
        }
    }

    public int find(int p) {
        if (p != this.id[p]) {
            this.id[p] = find(this.id[p]);
        }
        return this.id[p];
    }

    public long union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return 0;
        }
        long res = (sz[i] + sz[j]) * (sz[i] + sz[j] - 1) / 2 - sz[i] * (sz[i] - 1) / 2 - sz[j] * (sz[j] - 1) / 2;
        if (this.sz[i] < this.sz[j]) {
            this.id[i] = j;
            this.sz[j] += this.sz[i];
        } else {
            this.id[j] = i;
            this.sz[i] += this.sz[j];
        }
        return res;
    }
}
