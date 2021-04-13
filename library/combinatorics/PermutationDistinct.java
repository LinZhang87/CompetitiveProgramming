package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 6/9/19
 */

public class PermutationDistinct {
    private List<List<Integer>> perm = new ArrayList<>();

    /*
        Return all permutations of an integer array with all distinct elements
     */
    public List<List<Integer>> permutationsForUniqueIntegers(Integer[] nums) {
        permDistinctHelper(nums, new boolean[nums.length], new ArrayList<>());
        return perm;
    }

    private void permDistinctHelper(Integer[] nums, boolean[] picked, List<Integer> list) {
        if(list.size() == nums.length) {
            perm.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(picked[i]) {
                continue;
            }
            list.add(nums[i]);
            picked[i] = true;
            permDistinctHelper(nums, picked, list);
            list.remove(list.size() - 1);
            picked[i] = false;
        }
    }
}
