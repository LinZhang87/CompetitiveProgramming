package simulation;

/**
 * @author lzhang
 * @since 2/20/19
 */

public class MinParentheseRemoval {
    public int minParentheseRemoval(String parentheses) {
        int left = 0, removals = 0;
        for(int i = 0; i < parentheses.length(); i++) {
            char curr = parentheses.charAt(i);
            if(curr == ')' && left == 0) {
                removals++;
            }
            else if(curr == ')') {
                left--;
            }
            else {
                left++;
            }
        }
        removals += left;
        return removals;
    }
}
