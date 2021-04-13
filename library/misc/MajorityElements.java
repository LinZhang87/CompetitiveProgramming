package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzhang
 * @since 5/21/20
 */

/*
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array, find all the majority elements.

Example
Given [3,1,2,3,2,3,3,4,4,4] and k=3, return [3].

Challenge
O(n) time and O(k) extra space
 */

public class MajorityElements {
    public List<Integer> majorityElements(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            if(cnt.size() == k) {
                for(int key : cnt.keySet()) {
                    cnt.put(key, cnt.get(key) - 1);
                    if(cnt.get(key) == 0) {
                        cnt.remove(key);
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int key : cnt.keySet()) {
            cnt.put(key, 0);
        }
        for(int i = 0; i < nums.length; i++) {
            if(cnt.containsKey(nums[i])) {
                cnt.put(nums[i], cnt.get(nums[i]) + 1);
            }
        }
        for(int key : cnt.keySet()) {
            if(cnt.get(key) > nums.length / k) {
                ans.add(key);
            }
        }
        return ans;
    }
}
