package dp;

import java.util.Random;

/**
 * @author lzhang
 * @since 8/29/19
 */

/*
Find the minimum number of coins required to make n cents.

You can use standard American denominations, that is, 1¢, 5¢, 10¢, and 25¢.

For example, given n = 16, return 3 since we can make it with a 10¢, a 5¢, and a 1¢.
 */

/*
Does greedy algorithm work? Yes

Take the largest possible denomination until n is 0.
Proof of correctness:
Assume we pick coins from bigger denominations to smaller denominations.
say for n, the largest possible denomination is 25; The greedy solution takes
25. If there is a better solution by taking 10, 5 or 1, then it means n - 10 or n - 5 or n - 1 requires
fewer number of coins than n - 25. n - 25 requires k coins; n - 10 requires k' coins; k > k'
n - 25 = n - 10 - 10 - 5

case 1: n - 25 >= 25: each time a 25 is picked, the other case uses 2 two coins(10 and 5) until it falls in case 2.

case 2: n - 25 < 25: in this case picking 25 uses 2 fewer coins(10 and 5)

 */

/*
This greedy algorithm does not work on general cases: counter example: denominations = [1, 5, 7], n = 10. Greedy
solution gives 4 but optimal solution is 2. In this case, dp should be used.
 */
public class MinNumberOfCoins {
    public static int minNumOfCoins(int n) {
        int minCount = 0;
        while(n > 0) {
            if(n >= 25) {
                n -= 25;
            }
            else if(n >= 10) {
                n -= 10;
            }
            else if(n >= 5) {
                n -= 5;
            }
            else {
                n -= 1;
            }
            minCount++;
        }
        return minCount;
    }
    public static void main(String[] args) {
        Random random = new Random();
        int sum = random.nextInt(101);
        System.out.println(sum);
        System.out.println(minNumOfCoins(sum));
    }
}
