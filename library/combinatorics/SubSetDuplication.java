package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 9/22/20
 */

/*
    nums has duplicated integers
*/
public class SubSetDuplication {
    /*
        Backtracking
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        Arrays.sort(nums);
        search(ans, currList, nums, 0, false);
        return ans;
    }
    private void search(List<List<Integer>> ans, List<Integer> currList, int[] nums, int k, boolean prevSelected) {
        if(k == nums.length) {
            ans.add(new ArrayList<>(currList));
        }
        else {
            //the cases where we add nums[k]: nums[k] is the 1st element; or nums[k] is the first number of a unique group;
            //or nums[k] is not the first number of unique group but the previous duplicate has been selected.
            if(k == 0 || nums[k - 1] != nums[k] || prevSelected) {
                currList.add(nums[k]);
                search(ans, currList, nums, k + 1, true);
                currList.remove(currList.size() - 1);
            }
            search(ans, currList, nums, k + 1, false);
        }
    }

    /*
        BitMask
     */
    public List<List<Integer>> subsetsWithDupBitMask(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length, p = 1 << n;
        Arrays.sort(nums);
        for(int i = 0; i < p; i++) {
            List<Integer> curr = new ArrayList<>();
            boolean duplicate = false;
            for(int j = 0; j < n; j++) {
                if(((1 << j) & i) != 0) {
                    if(j > 0 && nums[j] == nums[j - 1] && ((1 << (j - 1)) & i) == 0) {
                        duplicate = true;
                        break;
                    }
                    else {
                        curr.add(nums[j]);
                    }
                }
            }
            if(!duplicate) ans.add(curr);
        }
        return ans;
    }
}
