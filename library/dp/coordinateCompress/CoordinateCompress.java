package dp.coordinateCompress;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author lzhang
 * @since 8/10/20
 */

public class CoordinateCompress {
    int maxCoord;

    class Segment {
        int l, r;
        Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }
        public String toString() {
            return "[" + l + ", " + r + "]";
        }
    }
    /*
        Given some 2D coordinates segments [L, R], the total number of segments can be up to 5000.
        There are no duplicated segments. L and R can go up to 10^5, do a coordinate compress such
        that a O(N^2) dp is feasible, N is the number of segments.

        maxCoord tells us how many unique coordinates we have after compressing, this can be used to
        initialize dp array.
     */

    void compressCoord(Segment[] segments) {
        TreeSet<Integer> ts = new TreeSet<>();
        for(Segment s : segments) {
            ts.add(s.l);
            ts.add(s.r);
        }
        ts.add(-(int)1e9);
        ts.add((int)1e9);
        Map<Integer, Integer> compressMap = new HashMap<>();
        for(int i : ts) {
            compressMap.put(i, compressMap.size());
        }
        for(Segment s : segments) {
            s.l = compressMap.get(s.l);
            s.r = compressMap.get(s.r);
        }
        maxCoord = compressMap.size();
    }
}
