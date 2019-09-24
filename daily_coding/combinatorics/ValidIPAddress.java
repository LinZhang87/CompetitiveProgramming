package combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 6/27/19
 */

/*
This problem was asked by Snapchat.

Given a string of digits, generate all possible valid IP address combinations.

IP addresses must follow the format A.B.C.D, where A, B, C, and D are numbers between 0 and 255. Zero-prefixed numbers, such as 01 and 065, are not allowed, except for 0 itself.

For example, given "2542540123", you should return ['254.25.40.123', '254.254.0.123'].
 */

public class ValidIPAddress {
    public static List<String> allValidIpAddresses(String digits) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), digits, 0, 0);
        return res;
    }
    private static void dfs(List<String> res, StringBuilder sb, String digits, int idx, int section) {
        if(idx == digits.length() && section == 4) {
            res.add(sb.toString());
            return;
        }
        else if(idx == digits.length() || section >= 4) {
            return;
        }
//        if(digits.charAt(idx) == '0') {
//            int len = sb.length();
//            if(section > 0) {
//                sb.append('.');
//            }
//            sb.append('0');
//            dfs(res, sb, digits, idx + 1, section + 1);
//            sb.setLength(len);
//        }
//        else {
//            for(int i = idx; i < idx + 3; i++) {
//                String sub = digits.substring(idx, i + 1);
//                int d = Integer.valueOf(sub);
//                if(d <= 255) {
//                    int len = sb.length();
//                    if(section > 0) {
//                        sb.append('.');
//                    }
//                    sb.append(sub);
//                    dfs(res, sb, digits, i + 1, section + 1);
//                    sb.setLength(len);
//                }
//            }
//        }
        int len = sb.length();
        if(section > 0) {
            sb.append('.');
        }
        sb.append(digits.charAt(idx));
        dfs(res, sb, digits, idx + 1, section + 1);
        sb.setLength(len);

        if(idx <= digits.length() - 2) {
            int twodigits = Integer.valueOf(digits.substring(idx, idx + 2));
            if(twodigits >= 10) {
                if(section > 0) {
                    sb.append('.');
                }
                sb.append(digits.substring(idx, idx + 2));
                dfs(res, sb, digits, idx + 2, section + 1);
                sb.setLength(len);
            }
        }

        if(idx <= digits.length() - 3) {
            int threedigits = Integer.valueOf(digits.substring(idx, idx + 3));
            if(threedigits >= 100  && threedigits <= 255) {
                if(section > 0) {
                    sb.append('.');
                }
                sb.append(digits.substring(idx, idx + 3));
                dfs(res, sb, digits, idx + 3, section + 1);
                sb.setLength(len);
            }
        }
    }

    public static void main(String[] args) {
        List<String> res = allValidIpAddresses("2542540123");
    }
}
