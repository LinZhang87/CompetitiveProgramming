package chapter2;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * @author lzhang
 * @since 6/26/19
 */


/*

Sample Input
This_is_a_[Beiju]_text
[[]][][]Happy_Birthday_to_Tsinghua_University

Sample Output
BeijuThis_is_a__text
Happy_Birthday_to_Tsinghua_University

 */
public class BrokenKeyboard {
    public static void main(String[] args) {
        //FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        try {
            FastScanner in = new FastScanner(new FileInputStream("src/input.in"));
            //PrintWriter out = new PrintWriter(new FileOutputStream("src/output.out"));

            String line;
            while((line = in.nextLine()) != null) {
                LinkedList<Character> linkedList = new LinkedList<>();
                int idx = 0;
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '[') {
                        idx = 0;
                    }
                    else if(line.charAt(i) == ']') {
                        idx = linkedList.size();
                    }
                    if(line.charAt(i) != '[' && line.charAt(i) != ']') {
                        linkedList.add(idx, line.charAt(i));
                        idx++;
                    }
                }
                StringBuilder sb = new StringBuilder();
                ListIterator<Character> listIterator = linkedList.listIterator(0);
                while(listIterator.hasNext()) {
                    sb.append(listIterator.next());
                }
                out.println(sb.toString());
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
