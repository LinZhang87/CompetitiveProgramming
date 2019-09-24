package bfs;

import java.util.*;

/**
 * @author lzhang
 * @since 7/13/19
 */

/*
This problem was asked by Flipkart.

Snakes and Ladders is a game played on a 10 x 10 board, the goal of which is get from square 1 to square 100.
On each turn players will roll a six-sided die and move forward a number of spaces equal to the result.
If they land on a square that represents a snake or ladder, they will be transported ahead or behind, respectively, to a new square.

Find the smallest number of turns it takes to play snakes and ladders.

For convenience, here are the squares representing snakes and ladders, and their outcomes:

snakes = {16: 6, 48: 26, 49: 11, 56: 53, 62: 19, 64: 60, 87: 24, 93: 73, 95: 75, 98: 78}
ladders = {1: 38, 4: 14, 9: 31, 21: 42, 28: 84, 36: 44, 51: 67, 71: 91, 80: 100}
 */

/*
First thought is to use dynamic programming. But the problem with dp is that it is hard to handle the case that a move lands
on a snake.

Since we are looking for the smallest number of turns to reach 100, we can use bfs.

 */

/*
Extra challenge: can you find the path that leads to 100 using the smallest number of turns?
 */

public class SnakesAndLadders {
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    int[] prevSquare = new int[101];

    public SnakesAndLadders() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        snakes.put(16, 6); snakes.put(48, 26); snakes.put(49, 11); snakes.put(56, 53); snakes.put(62, 19);
        snakes.put(64, 60); snakes.put(87, 24); snakes.put(93, 73); snakes.put(95, 75); snakes.put(98, 78);
        ladders.put(1, 38); ladders.put(4, 14); ladders.put(9, 31); ladders.put(21, 42); ladders.put(28, 84);
        ladders.put(36, 44); ladders.put(51, 67); ladders.put(71, 91); ladders.put(80, 100);
    }

    public int minTurns() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int turns = 0;
        boolean reachedEnd = false;
        q.add(0);

        while(q.size() > 0 && !reachedEnd) {
            int currSize = q.size();
            for(int i = 0; i < currSize; i++) {
                int square = q.poll();
                for(int move = 1; move <= 6; move++) {
                    int nextSquare = square + move;
                    if(nextSquare >= 100) {
                        reachedEnd = true;
                        prevSquare[100] = square;
                        break;
                    }
                    if(ladders.containsKey(nextSquare)) {
                        nextSquare = ladders.get(nextSquare);
                    }
                    else if(snakes.containsKey(nextSquare)) {
                        nextSquare = snakes.get(nextSquare);
                    }
                    if(!visited[nextSquare]) {
                        q.add(nextSquare);
                        visited[nextSquare] = true;
                        prevSquare[nextSquare] = square;
                    }
                }
            }
            turns++;
        }
        return turns;
    }

    public List<Integer> optimalPath() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int i = 100;
        dq.addFirst(100);

        while(i > 0) {
            dq.addFirst(prevSquare[i]);
            i = prevSquare[i];
        }
        return Arrays.asList(dq.toArray(new Integer[0]));
    }
    public static void main(String[] args) {
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        System.out.println(snakesAndLadders.minTurns());
        System.out.println(snakesAndLadders.optimalPath());
    }
}
