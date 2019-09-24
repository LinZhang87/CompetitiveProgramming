package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzhang
 * @since 8/26/19
 */

/*
A network consists of nodes labeled 0 to N. You are given a list of edges (a, b, t),
describing the time t it takes for a message to be sent from node a to node b.
Whenever a node receives a message, it immediately passes the message on to a neighboring node, if possible.

Assuming all nodes are connected, determine how long it will take for every node to receive a message that begins at node 0.

For example, given N = 5, and the following edges:

edges = [
    (0, 1, 5),
    (0, 2, 3),
    (0, 5, 4),
    (1, 3, 8),
    (2, 3, 1),
    (3, 5, 10),
    (3, 4, 5)
]
You should return 9, because propagating the message from 0 -> 2 -> 3 -> 4 will take that much time.
 */
public class MinTimeToReceiveMsg {
    public static int minTime(int N, List<Edge> edges) {
        boolean[] included = new boolean[N + 1];

        //pre-process input edges
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(Edge edge : edges) {
            graph.get(edge.getV1()).add(edge);
        }
        return 0;
    }
}
