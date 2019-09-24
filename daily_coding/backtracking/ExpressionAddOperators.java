package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 5/8/19
 */

public class ExpressionAddOperators {
    private static List<String> res = new ArrayList<>();
    public static List<String> addOperators(String num, int target) {
        for(int i = 1; i <= num.length(); i++) {
            String currStr = num.substring(0, i);
            long currNum = Long.parseLong(currStr);
            StringBuffer sb = new StringBuffer();
            sb.append(currNum);
            dfs(num, i, currNum, currNum, target, sb);

            if(num.charAt(0) == '0' && i == 1) {
                break;
            }
        }
        return res;
    }
    private static void dfs(String num, int currIdx, long currVal, long prevMul, int target, StringBuffer sb) {
        if(currIdx == num.length()) {
            if(currVal == Long.valueOf(target)) {
                res.add(sb.toString());
            }
            return;
        }
        for(int i = 1; i <= num.length() - currIdx; i++) {
            String currStr = num.substring(currIdx, currIdx + i);
            long currNum = Integer.parseInt(currStr);

            if(currNum == 90L) {
                System.out.println();
            }

            sb.append('+'); sb.append(currStr);
            dfs(num, currIdx + i, currVal + currNum, currNum, target, sb);
            sb.delete(sb.length() - 1 - currStr.length(), sb.length());

            sb.append('-'); sb.append(currStr);
            dfs(num, currIdx + i, currVal - currNum, -currNum, target, sb);
            sb.delete(sb.length() - 1 - currStr.length(), sb.length());

            sb.append('*'); sb.append(currStr);
            if(currVal == 1037 && prevMul == 1008) {
                System.out.println();
            }
            dfs(num, currIdx + i, currVal - prevMul + prevMul * currNum, prevMul * currNum, target, sb);
            sb.delete(sb.length() - 1 - currStr.length(), sb.length());

            if(num.charAt(currIdx) == '0' && i == 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        addOperators("3456237490", 9191);
    }
}
