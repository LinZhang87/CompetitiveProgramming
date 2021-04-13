package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 9/22/20
 */

public class PermutationDuplication {
    private List<List<Integer>> permWithDup = new ArrayList<>();

    /*
        Return all unique permutations of an integer array with duplicated elements
     */
    public List<List<Integer>> permutationsForDuplicatedIntegers(Integer[] nums) {
        if(nums.length == 0) {
            return permWithDup;
        }
        Arrays.sort(nums);
        permWithDupHelper(nums, new boolean[nums.length], new ArrayList<>());
        return permWithDup;
    }

    private void permWithDupHelper(Integer[] nums, boolean[] picked, List<Integer> list) {
        if(list.size() == nums.length) {
            permWithDup.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            //if nums[i] has been picked, or nums[i] has duplicates and its previous duplicate has not been picked yet
            //either case, we need to skip
            if(picked[i] || i > 0 && nums[i].compareTo(nums[i - 1]) == 0 && !picked[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            picked[i] = true;
            permWithDupHelper(nums, picked, list);
            list.remove(list.size() - 1);
            picked[i] = false;
        }
    }
}
