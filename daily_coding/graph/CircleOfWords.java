package graph;

import java.util.*;

/**
 * @author lzhang
 * @since 7/30/19
 */

/*
Given a list of words, determine whether the words can be chained to form a circle.
A word X can be placed in front of another word Y in a circle if the last character of X is same as the first character of Y.

For example, the words ['chair', 'height', 'racket', touch', 'tunic'] can form the following circle: chair --> racket --> touch --> height --> tunic --> chair.
 */

public class CircleOfWords {
    private static class Node {
        int label;
        Set<Integer> neighbors;

        Node(int label) {
            this.label = label;
            this.neighbors = new HashSet<>();
        }
    }
    public static boolean canFormCircle(String[] words) {
        Node[] nodes = new Node[words.length];
        Map<Character, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            nodes[i] = new Node(i);
            if(!map.containsKey(words[i].charAt(0))) {
                map.put(words[i].charAt(0), new HashSet<>());
            }
            map.get(words[i].charAt(0)).add(i);
        }
        for(int i = 0; i < words.length; i++) {
            Set<Integer> neighbors = map.get(words[i].charAt(words[i].length() - 1));
            if(neighbors != null) {
                neighbors.remove(i);
                nodes[i].neighbors = neighbors;
            }
        }
        for(int neighbor : nodes[0].neighbors) {
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            visited.add(neighbor);
            if(dfs(nodes, neighbor,0, visited)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(Node[] nodes, int currLabel, int startLabel, Set<Integer> visited) {
//        if(currLabel == startLabel) {
//            if(visited.size() == nodes.length) {
//                return true;
//            }
//            else {
//                return false;
//            }
//        }
        for(int neighbor : nodes[currLabel].neighbors) {
            if(!visited.contains(neighbor)) {
                visited.add(neighbor);
                boolean circle = dfs(nodes, neighbor, startLabel, visited);
                visited.remove(neighbor);
                if(circle) {
                    return true;
                }
            }
            else if(neighbor == startLabel && visited.size() == nodes.length) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"chair", "height", "racket", "touch", "tunic"};
        System.out.println(canFormCircle(words));
    }
}
