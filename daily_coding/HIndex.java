/**
 * @author lzhang
 * @since 7/26/19
 */

/*
This problem was asked by Palantir.

In academia, the h-index is a metric used to calculate the impact of a researcher's papers. It is calculated as follows:

A researcher has index h if at least h of her N papers have h citations each. If there are multiple h satisfying this formula, the maximum is chosen.

For example, suppose N = 5, and the respective citations of each paper are [4, 3, 0, 1, 5].
Then the h-index would be 3, since the researcher has 3 papers with at least 3 citations.

Given a list of paper citations of a researcher, calculate their h-index.
 */

/*
Can you do it in O(N) time?
 */
public class HIndex {
    public static int hIndex(int[] citations) {
        int left = 0, right = 0;
        for(int citation : citations) {
            right = Math.max(right, citation);
        }
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(check(citations, mid)) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return check(citations, right) ? right : left;
    }
    private static boolean check(int[] citations, int hIdx) {
        int count = 0;
        for(int citation : citations) {
            if(citation >= hIdx) {
                count++;
            }
            if(count >= hIdx) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] citations1 = {4,3,0,1,5}; //ans: 3
        int[] citations2 = {0,1,2,3,4,5};   //ans: 3
        int[] citations3 = {0,3,1,2};
        System.out.println(hIndex(citations2));
    }
}
