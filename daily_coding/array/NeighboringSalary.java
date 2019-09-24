package array;

import java.util.*;

/**
 * @author lzhang
 * @since 8/18/19
 */

/*
MegaCorp wants to give bonuses to its employees based on how many lines of codes they have written.
They would like to give the smallest positive amount to each worker consistent with the constraint
that if a developer has written more lines of code than their neighbor, they should receive more money.

Given an array representing a line of seats of employees at MegaCorp, determine how much each one should get paid.

For example, given [10, 40, 200, 1000, 60, 30], you should return [1, 2, 3, 4, 2, 1].
 */

/*
Algorithm:
Find all valley points in the input array and add them to a queue. Do a bfs style scan, for each position, set its
neighbors of higher value to the next higher number. Only add neighboring positions of higher value. Each position will
be updated at most twice(once from left side, once from right side) and the max value is taken. Because each valley point
is separate away from each other by the peak points and each scan stops at local peak point. This algorithm takes O(N)
runtime.

If there are adjacent duplicated numbers, then the above algorithm won't work. We can do a preprocessing step to exclude
adjacent duplicates as they will have the same final answer. Then assign the correct values right before returning.
 */
public class NeighboringSalary {
    public static int[] neighboringSalary(int[] work) {
        if(work.length == 0) {
            return new int[0];
        }
        else if(work.length == 1) {
            return new int[]{1};
        }

        List<Integer> nonAdjacentDupList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        nonAdjacentDupList.add(work[0]);
        for(int i = 1; i < work.length; i++) {
            if(work[i] != nonAdjacentDupList.get(nonAdjacentDupList.size() - 1)) {
                nonAdjacentDupList.add(work[i]);
            }
        }
        if(nonAdjacentDupList.size() == 1) {
            return new int[]{1};
        }
        int[] nonAdjacentDupArray = new int[nonAdjacentDupList.size()];
        int[] salary = new int[nonAdjacentDupList.size()];
        Arrays.fill(salary, 1);
        for(int i = 0; i < nonAdjacentDupList.size(); i++) {
            nonAdjacentDupArray[i] = nonAdjacentDupList.get(i);
        }
        if(nonAdjacentDupArray[0] < nonAdjacentDupArray[1]) {
            q.add(0);
        }
        if(nonAdjacentDupArray[nonAdjacentDupArray.length - 1] < nonAdjacentDupArray[nonAdjacentDupArray.length - 2]) {
            q.add(nonAdjacentDupArray.length - 1);
        }
        for(int i = 1; i < nonAdjacentDupArray.length - 1; i++) {
            if(nonAdjacentDupArray[i] < nonAdjacentDupArray[i - 1] && nonAdjacentDupArray[i] < nonAdjacentDupArray[i + 1]) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            if(curr > 0 && nonAdjacentDupArray[curr - 1] > nonAdjacentDupArray[curr]) {
                salary[curr - 1] = Math.max(salary[curr - 1], salary[curr] + 1);
                q.add(curr - 1);
            }
            if(curr < nonAdjacentDupArray.length - 1 && nonAdjacentDupArray[curr + 1] > nonAdjacentDupArray[curr]) {
                salary[curr + 1] = Math.max(salary[curr + 1], salary[curr] + 1);
                q.add(curr + 1);
            }
        }
        int[] res = new int[work.length];
        int i = 0, j = 0;
        while(i < res.length) {
            while(i > 0  && i < res.length && work[i] == work[i - 1]) {
                res[i] = res[i - 1];
                i++;
            }
            if(i == res.length) {
                break;
            }
            res[i] = salary[j];
            i++;
            j++;
        }
        return res;
    }

    public static int[] neighboringSalary1(int[] work) {
        int n = work.length;
        int[] res = new int[n];
        //Arrays.fill(res, 1);
        List<Integer> list = new ArrayList<>();
        list.add(work[0]);
        for(int i = 1; i < n; i++) {
            if(work[i] != list.get(list.size() - 1)) {
                list.add(work[i]);
            }
        }
        Integer[] work1 = list.toArray(new Integer[list.size()]);
        int[] bonus = new int[list.size()];
        Arrays.fill(bonus, 1);
        for(int i = 1; i < work1.length; i++) {
            if(work1[i] > work1[i - 1]) {
                bonus[i] = bonus[i - 1] + 1;
            }
        }
        for(int i = work1.length - 2; i >= 0; i--) {
            if(work1[i] > work1[i + 1]) {
                bonus[i] = Math.max(bonus[i], bonus[i + 1] + 1);
            }
        }

        int i = 0, j = 0;
        while(i < n) {
            while(i > 0  && i < n && work[i] == work[i - 1]) {
                res[i] = res[i - 1];
                i++;
            }
            if(i == n) {
                break;
            }
            res[i] = bonus[j];
            i++;
            j++;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] a0 = {10, 40, 200, 1000, 60, 30};
        int[] a1 = {10,10,10,40,40,200,1000,1000,60,30};
        int[] a2 = {40,40,40,30,20,10,30,50,20};
        System.out.println(Arrays.toString(neighboringSalary1(a2)));
    }
}
