package binarysearch;

import randomized.ArrayShuffle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author lzhang
 * @since 12/2/19
 */

/*
    In computer science, patience sorting is a sorting algorithm inspired by, and named after, the card game patience.
    A variant of the algorithm efficiently computes the length of a longest increasing subsequence in a given array.

    The algorithm's name derives from a simplified variant of the patience card game. This game begins with a shuffled deck of cards.
    These cards are dealt one by one into a sequence of piles on the table, according to the following rules.

    1. Initially, there are no piles. The first card dealt forms a new pile consisting of the single card.
    2. Each subsequent card is placed on the leftmost existing pile whose top card has a value greater than or equal the new card's value,
    or to the right of all of the existing piles, thus forming a new pile.
    3. When there are no more cards remaining to deal, the game ends.

    This card game is turned into a two-phase sorting algorithm, as follows. Given an array of n elements from some totally ordered domain,
    consider this array as a collection of cards and simulate the patience sorting game. When the game is over, recover the sorted sequence
    by repeatedly picking off the minimum visible card; in other words, perform a k-way merge of the p piles, each of which is internally sorted.

 */

public class PatienceSorting {
    public static void patienceSorting(int[] a) {
        List<List<Integer>> piles = new ArrayList<>();
        for(int i = 0; i < a.length; i++) {
            int pIdx = findLeftMostPile(piles, a[i]);
            if(pIdx < 0) {
                List<Integer> newPile = new ArrayList<>();
                newPile.add(a[i]);
                piles.add(newPile);
            }
            else {
                List<Integer> toAddPile = piles.get(pIdx);
                toAddPile.add(a[i]);
            }
        }

        PriorityQueue<int[]> minPq = new PriorityQueue<>(Comparator.comparingInt(b -> b[0]));
        for(int i = 0; i < piles.size(); i++) {
            List<Integer> currPile = piles.get(i);
            minPq.add(new int[]{currPile.remove(currPile.size() - 1), i});
        }
        for(int i = 0; i < a.length; i++) {
            int[] currMin = minPq.poll();
            a[i] = currMin[0];
            if(piles.get(currMin[1]).size() > 0) {
                minPq.add(new int[]{piles.get(currMin[1]).remove(piles.get(currMin[1]).size() - 1), currMin[1]});
            }
        }
    }

    //Find the index of the left most piles whose top card is >= the current processing a[i], return -1 if none
    private static int findLeftMostPile(List<List<Integer>> piles, int val) {
        if(piles.size() == 0) {
            return -1;
        }
        int l = 0, r = piles.size() - 1;
        while(l < r - 1) {
            int mid = l + (r - l) / 2;
            List<Integer> p = piles.get(mid);
            if(p.get(p.size() - 1) >= val) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        if(piles.get(l).get(piles.get(l).size() - 1) >= val) {
            return l;
        }
        else if(piles.get(r).get(piles.get(r).size() - 1) >= val) {
            return r;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 100;
        int[] a = new int[100];
        for(int i = 0; i < 100; i++) {
            a[i] = i;
        }
        ArrayShuffle arrayShuffle = new ArrayShuffle();
        arrayShuffle.shuffle(a);
        patienceSorting(a);
        for(int i = 1; i < a.length; i++) {
            if(a[i] < a[i - 1]) {
                System.out.println("Fail");
                break;
            }
        }
    }
}
