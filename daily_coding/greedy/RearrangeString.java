package greedy;

/**
 * @author lzhang
 * @since 7/15/19
 */

/*
This problem was asked by IBM.

Given a string with repeated characters, rearrange the string so that no two adjacent characters are the same. If this is not possible, return None.

For example, given "aaabbc", you could return "ababac". Given "aaab", return None.
 */

/*
    for simplicity, assume there are only a-z as letters.
 */

/*
Algorithm:

1. count the frequency of all letters and find the max frequency. If it is bigger than (s.length() + 1) / 2, return empty string as it is impossible to
have a rearrangement with no adjacent same letters.
2. otherwise, starting from position 0, put one letter of the max frequency at each even index position until we've used up all counts of this letter.
3. if there are still unfilled even positions, use the rest letters to fill in. (which letter is picked does not matter anymore)
4. starting from position 1, fill in all the odd index positions with the remaining unused letters.
 */

public class RearrangeString {
    public static String rearrange(String s) {
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int maxIdx = 0, maxCount = 0;
        for(int i = 0; i < 26; i++) {
            if(maxCount < count[i]) {
                maxCount = count[i];
                maxIdx = i;
            }
        }
        if(maxCount > (s.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[s.length()];
        int even = 0, odd = 1, used = 0;
        for(; count[maxIdx] > 0; even += 2) {
            res[even] = (char)('a' + maxIdx);
            count[maxIdx]--;
            used++;
        }
        int j = 0;
        for(; j < 26; j++) {
            if(used == res.length) {
                return String.valueOf(res);
            }
            if(count[j] == 0) {
                continue;
            }
            while(even < s.length() && count[j] > 0) {
                res[even] = (char)('a' + j);
                even += 2;
                count[j]--;
                used++;
            }
            if(even >= s.length()) {
                break;
            }
        }
        for(; j < 26; j++) {
            if(used == res.length) {
                return String.valueOf(res);
            }
            if(count[j] == 0) {
                continue;
            }
            while(odd < s.length() && count[j] > 0) {
                res[odd] = (char)('a' + j);
                odd += 2;
                count[j]--;
                used++;
            }
        }
        return String.valueOf(res);
    }
    public static void main(String[] args) {
        System.out.println(rearrange("bfrbs"));
    }
}
