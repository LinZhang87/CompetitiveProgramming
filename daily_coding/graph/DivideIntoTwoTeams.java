package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzhang
 * @since 9/14/19
 */

/*
A teacher must divide a class of students into two teams to play dodgeball. Unfortunately, not all the kids get along,
and several refuse to be put on the same team as that of their enemies.

Given an adjacency list of students and their enemies, write an algorithm that finds a satisfactory pair of teams, or returns False if none exists.

For example, given the following enemy graph you should return the teams {0, 1, 4, 5} and {2, 3}.

students = {
    0: [3],
    1: [2],
    2: [1, 4],
    3: [0, 4, 5],
    4: [2, 3],
    5: [3]
}
On the other hand, given the input below, you should return False.

students = {
    0: [3],
    1: [2],
    2: [1, 3, 4],
    3: [0, 2, 4, 5],
    4: [2, 3],
    5: [3]
}
 */

/*
This problem can be solved using a special case of graph coloring, bipartite graph. A graph is bipartite exactly when it does not contain a cycle with an odd number of edges.
 */
public class DivideIntoTwoTeams {
    private static final int RED = -1;
    private static final int BLUE = 1;
    private static final int NO_COLOR = 0;
    public static List<List<Integer>> divideIntoTwoTeamsDfs(int[][] enemies) {
        int[] colors = new int[enemies.length];
        for(int i = 0; i < enemies.length; i++) {
            if(colors[i] == NO_COLOR) {
                if(!graphColoringDfs(enemies, colors, i, RED)) {
                    return null;
                }
            }
        }
        List<List<Integer>> teams = new ArrayList<>();
        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == RED) {
                team1.add(i);
            }
            else {
                team2.add(i);
            }
        }
        teams.add(team1); teams.add(team2);
        return teams;
    }
    private static boolean graphColoringDfs(int[][] g, int[] colors, int currNode, int color) {
        if(colors[currNode] != NO_COLOR && colors[currNode] != color) {
            return false;
        }
        else if(colors[currNode] != NO_COLOR) {
            return true;
        }
        colors[currNode] = color;
        for(int neighbor : g[currNode]) {
            if(!graphColoringDfs(g, colors, neighbor, -1 * color)) {
                return false;
            }
        }
        return true;
    }

    public static List<List<Integer>> divideIntoTwoTeamsBfs(int[][] enemies) {
        int[] colors = new int[enemies.length];
        for(int i = 0; i < enemies.length; i++) {
            if(colors[i] == NO_COLOR) {
                colors[i] = RED;
                if(!graphColoringBfs(enemies, colors, i)) {
                    return null;
                }
            }
        }
        List<List<Integer>> teams = new ArrayList<>();
        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == RED) {
                team1.add(i);
            }
            else {
                team2.add(i);
            }
        }
        teams.add(team1); teams.add(team2);
        return teams;
    }
    private static boolean graphColoringBfs(int[][] g, int[] colors, int currNode) {
        Queue<Integer> q = new LinkedList<>();
        q.add(currNode);

        while(q.size() > 0) {
            int curr = q.poll();
            for(int neighbor : g[curr]) {
                if(colors[neighbor] != NO_COLOR && colors[neighbor] == colors[curr]) {
                    return false;
                }
                else if(colors[neighbor] == NO_COLOR) {
                    colors[neighbor] = -1 * colors[curr];
                    q.add(neighbor);
                }
            }
        }
        return true;
    }
}
