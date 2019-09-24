package datastructures.hasing;

import java.util.*;

/**
 * @author lzhang
 * @since 9/9/19
 */

/*
You are given a list of (website, user) pairs that represent users visiting websites.
Come up with a program that identifies the top k pairs of websites with the greatest similarity.

For example, suppose k = 1, and the list of tuples is:

[('a', 1), ('a', 3), ('a', 5),
 ('b', 2), ('b', 6),
 ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5)
 ('d', 4), ('d', 5), ('d', 6), ('d', 7),
 ('e', 1), ('e', 3), ('e': 5), ('e', 6)]
Then a reasonable similarity metric would most likely conclude that a and e are the most similar, so your program should return [('a', 'e')].
 */


/*
While there are several ways to mathematically represent similarity, one of the most straightforward is known as the Jaccard index,
and is computed for two sets by dividing the size of their intersection by the size of their union.
 */

public class SimilarWebsites {
    static class Log {
        String website;
        int user;

        Log(String website, int user) {
            this.website = website;
            this.user = user;
        }
    }
    static class WebsitePair {
        int intersection, union;
        String w1, w2;

        WebsitePair(int intersection, int union, String w1, String w2) {
            this.intersection = intersection;
            this.union = union;
            this.w1 = w1;
            this.w2 = w2;
        }
    }
    public static WebsitePair[] topKPairsOfSimilarWebsites(Log[] logs, int k) {
        Map<String, Set<Integer>> logMap = new HashMap<>();
        List<String> websites = new ArrayList<>();
        PriorityQueue<WebsitePair> minPq = new PriorityQueue<>((wp1, wp2) -> {return wp1.intersection * wp2.union - wp2.intersection * wp1.union; });
        //O(N) runtime, N is the number of websites
        for(Log log : logs) {
            if(!logMap.containsKey(log.website)) {
                logMap.put(log.website, new HashSet<>());
                websites.add(log.website);
            }
            logMap.get(log.website).add(log.user);
        }
        //O(N^2 * (M + logk))
        for(int i = 0; i < websites.size(); i++) {
            for(int j = i + 1; j < websites.size(); j++) {
                //O(M) time, M is the number of users
                WebsitePair wp = calculateSimiliarity(websites.get(i), logMap.get(websites.get(i)), websites.get(j), logMap.get(websites.get(j)));
                //O(logk)
                if(minPq.size() < k) {
                    minPq.add(wp);
                }
                else {
                    WebsitePair minWp = minPq.peek();
                    if(minWp.intersection * wp.union < wp.intersection * minWp.union) {
                        minPq.poll();
                        minPq.add(wp);
                    }
                }
            }
        }
        WebsitePair[] res = new WebsitePair[k];
        while(minPq.size() > 0) {
            res[minPq.size() - 1] = minPq.poll();
        }
        return res;
    }
    private static WebsitePair calculateSimiliarity(String w1, Set<Integer> users1, String w2, Set<Integer> users2) {
        int intersection = 0;
        for(int u1 : users1) {
            if(users2.contains(u1)) {
                intersection++;
            }
        }
        return new WebsitePair(intersection, users1.size() + users2.size() - intersection, w1, w2);
    }
    public static void main(String[] args) {

    }
}
