package setcover;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lzhang
 * @since 9/19/19
 */

/*
At a popular bar, each customer has a set of favorite drinks, and will happily accept any drink among this set.
For example, in the following situation, customer 0 will be satisfied with drinks 0, 1, 3, or 6.

preferences = {
    0: [0, 1, 3, 6],
    1: [1, 4, 7],
    2: [2, 4, 7, 5],
    3: [3, 2, 5],
    4: [5, 8]
}
A lazy bartender working at this bar is trying to reduce his effort by limiting the drink recipes he must memorize.
Given a dictionary input such as the one above, return the fewest number of drinks he must learn in order to satisfy all customers.

For the input above, the answer would be 2, as drinks 1 and 5 will satisfy everyone.
 */


/*
NP-hard: set cover problem?
 */
public class LazyBartender {
    private static int minDrinks = Integer.MAX_VALUE;
    public static int minimumDrinks(int[][] drinks) {
        int n = drinks.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < drinks[i].length; j++) {
                if(!map.containsKey(drinks[i][j])) {
                    map.put(drinks[i][j], new HashSet<>());
                }
                map.get(drinks[i][j]).add(i);
            }
        }
        setCover(map, new HashSet<>(), n, new boolean[map.size()]);
        return minDrinks;
    }
    private static void setCover(Map<Integer, Set<Integer>> map, Set<Integer> covered, int customers, boolean[] used) {
        if(covered.size() == customers) {
            int count = 0;
            for(int i = 0; i < used.length; i++) {
                if(used[i]) {
                    count++;
                }
            }
            minDrinks = Math.min(minDrinks, count);
            return;
        }
        for(int i = 0; i < used.length; i++) {
            if(used[i]) {
                continue;
            }
            used[i] = true;
            Set<Integer> uncovered = new HashSet<>();
            for(int k : map.get(i)) {
                if(!covered.contains(k)) {
                    uncovered.add(k);
                }
            }
            covered.addAll(uncovered);
            setCover(map, covered, customers, used);
            used[i] = false;
            covered.removeAll(uncovered);
        }
    }
    public static void main(String[] args) {
        int[][] drinks = {{0,1,3,6},{1,4,7},{2,4,7,5},{3,2,5},{5,8}};
        System.out.println(minimumDrinks(drinks));
    }
}
