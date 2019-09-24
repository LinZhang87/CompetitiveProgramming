import java.util.*;

/**
 * @author lzhang
 * @since 7/10/19
 */

/*
This problem was asked by Airbnb.

You come across a dictionary of sorted words in a language you've never seen before.
Write a program that returns the correct order of letters in this language.

For example, given ['xww', 'wxyz', 'wxyw', 'ywx', 'ywz'], you should return ['x', 'z', 'w', 'y'].
 */

/*
Thought process: each letter represents a graph node in a directed graph. By processing adjacent words we can get all
the edges in this graph. Then this problem becomes a topological sort problem except that there will be only on valid
order.

Use a Set not List to avoid duplicated edge!
 */
public class OrderOfLetters {
    private static Map<Character, Set<Character>> directedGraph = new HashMap<>();
    private static void initDirectedGraph(String[] words) {
        for(int i = 1; i < words.length; i++) {
            char[] order = getRelativeOrder(words[i - 1], words[i]);
            if(order != null) {
                if(!directedGraph.containsKey(order[0])) {
                    directedGraph.put(order[0], new HashSet<>());
                }
                if(!directedGraph.containsKey(order[1])) {
                    directedGraph.put(order[1], new HashSet<>());
                }
                directedGraph.get(order[0]).add(order[1]);
            }
        }
    }
    public static List<Character> getOrderOfLetters(String[] words) {
        initDirectedGraph(words);
        //return topologicalSortDfs();
        return topologicalSortBfs();
    }
    private static char[] getRelativeOrder(String w1, String w2) {
        int i = 0;
        while(i < w1.length() && i < w2.length()) {
            if(w1.charAt(i) != w2.charAt(i)) {
                return new char[]{w1.charAt(i), w2.charAt(i)};
            }
            i++;
        }
        return null;
    }
    private static List<Character> topologicalSortDfs() {
        List<Character> list = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();

        for(char node : directedGraph.keySet()) {
            if(!visited.contains(node)) {
                topologicalSortDfsHelper(node, visited, stack);
            }
        }

        while(stack.size() > 0) {
            list.add(stack.pop());
        }
        return list;
    }
    private static void topologicalSortDfsHelper(char node, Set<Character> visited, Stack<Character> stack) {
        visited.add(node);
        for(char neighbor : directedGraph.get(node)) {
            if(!visited.contains(neighbor)) {
                topologicalSortDfsHelper(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }
    private static List<Character> topologicalSortBfs() {
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> incomingEdgeCountMap = new HashMap<>();
        Set<Character> noIncomingEdge = new HashSet<>(directedGraph.keySet());

        for(char node : directedGraph.keySet()) {
            for(char neighbor : directedGraph.get(node)) {
                incomingEdgeCountMap.put(neighbor, incomingEdgeCountMap.getOrDefault(neighbor, 0) + 1);
                noIncomingEdge.remove(neighbor);
            }
        }

        Queue<Character> q = new LinkedList<>();
        Iterator<Character> iter = noIncomingEdge.iterator();
        char start = iter.next();
        q.add(start);
        list.add(start);

        while(q.size() > 0) {
            char curr = q.poll();
            for(char neighbor : directedGraph.get(curr)) {
                incomingEdgeCountMap.put(neighbor, incomingEdgeCountMap.get(neighbor) - 1);
                if(incomingEdgeCountMap.get(neighbor) == 0) {
                    list.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
        return list;
    }
    public static void main(String[] args) {
        String[] words = {"xww", "wxyz", "wxyw", "ywx", "ywz"};
        System.out.println(Arrays.toString(getOrderOfLetters(words).toArray(new Character[0])));
    }
}
