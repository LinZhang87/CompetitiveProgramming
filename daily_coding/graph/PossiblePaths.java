package graph;/*
TODO: this naive dfs solution is not optimal as it does not memorize any paths it has already visited. Instead, it just blindly visits each node
 */


import java.util.*;

/**
 * @author lzhang
 * @since 4/2/19
 */

public class PossiblePaths {
    static class Segment {
        String from, to;
        Segment(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }
    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;

    private static Map<String, Set<String>> graph = new HashMap<>();
    private static Map<String, Integer> state = new HashMap<>();
    private static Map<String, List<LinkedList<String>>> paths = new HashMap<>();

    public static List<LinkedList<String>> getAllPossiblePaths(Segment[] segments) {

        //construct graph
        for(Segment segment : segments) {
            if(!graph.containsKey(segment.from)) {
                graph.put(segment.from, new HashSet<>());
            }
            if(!graph.containsKey(segment.to)) {
                graph.put(segment.to, new HashSet<>());
            }
            Set<String> tos = graph.get(segment.from);
            tos.add(segment.to);
        }

        //preprocess to remove self circle
        for(String node : graph.keySet()) {
            Set<String> neighbors = graph.get(node);
            if(neighbors.contains(node)) {
                neighbors.remove(node);
            }
        }

        //visit state map
        for(String node: graph.keySet()) {
            state.put(node, UNVISITED);
        }

        //dfs on each node in this graph to find all paths that do not have cycle in it
        for(String node : graph.keySet()) {
            dfs(node);
        }
        List<LinkedList<String>> ans = new ArrayList<>();
        for(List<LinkedList<String>> pathFromOneNode : paths.values()) {
            ans.addAll(pathFromOneNode);
        }
        return ans;
    }
    private static List<LinkedList<String>> dfs(String node) {
        if(state.get(node) == VISITED) {
            return paths.get(node);
        }
        else if(state.get(node) == VISITING) {
            LinkedList<String> cycle = new LinkedList<>();
            List<LinkedList<String>> cyclePaths = new ArrayList<>();
            cyclePaths.add(cycle);
            return cyclePaths;
        }
        state.put(node, VISITING);
        Set<String> neighbors = graph.get(node);
        if(neighbors.size() == 0) {
            LinkedList<String> p = new LinkedList<>(); p.addFirst(node);
            List<LinkedList<String>> ps = new ArrayList<>(); ps.add(p);
            paths.put(node, ps);
            return ps;
        }
        List<LinkedList<String>> newPaths = new ArrayList<>();
        for(String neighbor : neighbors) {
            List<LinkedList<String>> neighborPaths = dfs(neighbor);
            for(LinkedList<String> path : neighborPaths) {
                LinkedList<String> newPath = new LinkedList<>(path);
                newPath.addFirst(node);
                newPaths.add(newPath);
            }
        }
        paths.put(node, newPaths);
        state.put(node, VISITED);
        return newPaths;
    }


    public static void main(String[] args) {
//        Segment segment1 = new Segment("A", "B");
//        Segment segment2 = new Segment("A", "C");
//        Segment segment3 = new Segment("A", "D");
//        Segment segment4 = new Segment("B", "D");
//        Segment segment5 = new Segment("B", "A");
//        Segment segment6 = new Segment("C", "E");
//        Segment segment7 = new Segment("C", "F");
//        Segment segment8 = new Segment("C", "D");
//        Segment segment9 = new Segment("D", "G");
//        Segment segment10 = new Segment("D", "F");
//        Segment segment11 = new Segment("D", "E");
//        Segment segment12 = new Segment("F", "E");
//        Segment segment13 = new Segment("F", "A");
//        Segment segment14 = new Segment("F", "B");
//        Segment segment15 = new Segment("G", "A");
//        Segment segment16 = new Segment("G", "B");
//        Segment segment17 = new Segment("G", "E");
//
//        Segment[] segments = {segment1, segment2, segment3, segment4, segment5, segment6, segment7, segment8, segment9,
//                                segment10, segment11, segment12, segment13, segment14, segment15, segment16, segment17};

        Segment segment1 = new Segment("A", "B");
        Segment segment2 = new Segment("B", "C");
        Segment segment3 = new Segment("C", "D");
        Segment segment4 = new Segment("D", "E");
        Segment segment5 = new Segment("E", "A");
        Segment segment6 = new Segment("D", "B");

        Segment[] segments = {segment1, segment2, segment3, segment4, segment5, segment6};
        List<LinkedList<String>> paths = getAllPossiblePaths(segments);
        for(int i = 0; i < paths.size(); i++) {
            System.out.println(paths.get(i));
        }
    }
}
