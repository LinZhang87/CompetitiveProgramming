package combinatorics;

import java.util.*;

/**
 * @author lzhang
 * @since 6/9/19
 */

/*
    nums only has unique integers
 */
public class SubSetDistinct {
    /*
        Backtracking
     */
    public List<List<Integer>> subSetBacktracking(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        search(ans, currList, nums, 0);
        return ans;
    }

    private void search(List<List<Integer>> ans, List<Integer> currList, int[] nums, int k) {
        if(k == nums.length) {
            ans.add(new ArrayList<>(currList));
        }
        else {
            //for each nums[i], it has 2 cases: add or not add
            //add nums[k] and keep searching
            currList.add(nums[k]);
            search(ans, currList, nums, k + 1);
            //does not add nums[k] and keep searching
            currList.remove(currList.size() - 1);
            search(ans, currList, nums, k + 1);
        }
    }

    /*
        nums only has unique integers, bit mask implementation;
        This only works when the number of integers is <= 31; In a regular cp setting, 2^20 is
        already about 10^6, so using an integer as bit mask is sufficient.
     */
    public List<List<Integer>> subSetBitMask(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length, p = 1 << n;
        for(int i = 0; i < p; i++) {
            List<Integer> curr = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                if(((1 << j) & i) != 0) {
                    curr.add(nums[j]);
                }
            }
            ans.add(curr);
        }
        return ans;
    }
}
