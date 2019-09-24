package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lzhang
 * @since 3/29/19
 */

public class ListPermutation {
    public static List<List<Integer>> generateUniqueListPermutations(List<List<Integer>> lists) {
        List<List<Integer>> permutations = new ArrayList<>();
        //sort each input list
        if(lists == null || lists.size() == 0) {
            return permutations;
        }
        for(List<Integer> list : lists) {
            Collections.sort(list);
        }
        permutationHelper(lists, permutations, new ArrayList<>(), 0);
        return permutations;
    }
    private static void permutationHelper(List<List<Integer>> lists, List<List<Integer>> permutations, List<Integer> list, int idx) {
        if(idx == lists.size()) {
            permutations.add(new ArrayList<>(list));
            return;
        }
        List<Integer> currList = lists.get(idx);
        for(int i = 0; i < currList.size(); i++) {
            if(i > 0 && currList.get(i) == currList.get(i - 1)) {
                continue;
            }
            list.add(currList.get(i));
            permutationHelper(lists, permutations, list, idx + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(); list1.add(1); list1.add(2); list1.add(3); list1.add(4);
        List<Integer> list2 = new ArrayList<>(); list2.add(11); list2.add(22); list2.add(33);
        List<Integer> list3 = new ArrayList<>(); list3.add(111); list3.add(222);
        List<List<Integer>> lists = new ArrayList<>(); lists.add(list1); lists.add(list2); lists.add(list3);
        generateUniqueListPermutations(lists);
    }
}
