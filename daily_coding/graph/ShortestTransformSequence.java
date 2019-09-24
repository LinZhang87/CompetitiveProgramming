package graph; /**
 * @author lzhang
 * @since 5/15/19
 */

/*
Given a start word, an end word, and a dictionary of valid words, find the shortest transformation sequence from start
to end such that only one letter is changed at each step of the sequence, and each transformed word exists in the dictionary.
If there is no possible transformation, return null. Each word in the dictionary have the same length as start and end and is lowercase.

For example, given start = "dog", end = "cat", and dictionary = {"dot", "dop", "dat", "cat"}, return ["dog", "dot", "dat", "cat"].

Given start = "dog", end = "cat", and dictionary = {"dot", "tod", "dat", "dar"}, return null as there is no possible transformation from dog to cat.
 */
import java.util.*;

public class ShortestTransformSequence {
    static class GraphNode {
        String word;
        Set<String> neighbors;
        String from;

        GraphNode(String word) {
            this.word = word;
            this.neighbors = new HashSet<>();
        }

    }
    public static List<String> shortestTransformSequence(String start, String end, List<String> dict) {
        List<String> seq = new ArrayList<>();

        Set<String> dictSet = new HashSet<>();
        dictSet.addAll(dict);
        if(!dictSet.contains(start) || !dictSet.contains(end)) {
            return null;
        }
        dictSet.add(start); dictSet.add(end);

        Map<String, GraphNode> graph = new HashMap<>();
        for(String s : dictSet) {
            graph.put(s, new GraphNode(s));
        }

        List<String> dictList = new ArrayList<>(dictSet);

        for(int i = 0; i < dictList.size() - 1; i++) {
            for(int j = i + 1; j < dictList.size(); j++) {
                if(isOneLetterOff(dictList.get(i), dictList.get(j))) {
                    graph.get(dictList.get(i)).neighbors.add(dictList.get(j));
                    graph.get(dictList.get(j)).neighbors.add(dictList.get(i));
                }
            }
        }

        boolean exist = false;
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(start);
        visited.add(start);

        while(!q.isEmpty()) {
            String curr = q.poll();
            if(curr.equals(end)) {
                exist = true;
                break;
            }
            for(String neighbor : graph.get(curr).neighbors) {
                if(!visited.contains(neighbor)) {
                    graph.get(neighbor).from = curr;
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        if(!exist) {
            return null;
        }

        String path = end;
        while(!path.equals(start)) {
            seq.add(path);
            path = graph.get(path).from;
        }
        seq.add(path);
        Collections.reverse(seq);

        return seq;
    }

    private static boolean isOneLetterOff(String s1, String s2) {
        int cnt = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                cnt++;
            }
        }
        return cnt == 1;
    }
    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        List<String> dict1 = new ArrayList<>();
        List<String> dict2 = new ArrayList<>();
        dict.add("dot"); dict.add("dop"); dict.add("dat"); dict.add("cat");
        dict1.add("dot"); dict1.add("tod"); dict1.add("dat"); dict1.add("dar");
        String start = "cat", end = "cat";
        List<String> seq = shortestTransformSequence(start, end, dict);
    }
}
