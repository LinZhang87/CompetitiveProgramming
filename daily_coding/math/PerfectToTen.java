package math;

/**
 * @author lzhang
 * @since 2/4/19
 */

public class PerfectToTen {
    public static int NthPerfectToTen(int n) {
        int count = 0;
        for(int i = 0; i < 20000; i++) {
            int d = i;
            int sum = 0;
            while(d > 0) {
                sum += d % 10;
                d = d / 10;
            }
            if(sum == 10) {
                count++;
                if(n == count) {
                    System.out.println(i);
                }
            }
        }
        //System.out.println(count);
        return 0;
    }
    public static void main(String[] args) {
        System.out.println("hello\n");
        NthPerfectToTen(256);
    }
}
