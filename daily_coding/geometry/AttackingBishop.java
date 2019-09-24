package geometry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 2/3/19
 */

public class AttackingBishop {
    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 31 * x + 37 * y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Point p = (Point) obj;
            if (x != p.x || y != p.y) {
                return false;
            }
            return true;
        }
    }

    public int countAttackingBishopPairs(Point[] points, int M) {
        Map<Point, Integer> posMap = new HashMap<>();
        Map<Point, Integer> negMap = new HashMap<>();
        for (Point point : points) {
            Point p1 = new Point(point.x - Math.min(point.x, point.y), point.y - Math.min(point.x, point.y));
            Point p2 = new Point(point.x - Math.min(point.x, M - point.y), point.y + Math.min(point.x, M - point.y));
            if (!posMap.containsKey(p1)) {
                posMap.put(p1, 1);
            } else {
                posMap.put(p1, posMap.get(p1) + 1);
            }
            if (!negMap.containsKey(p2)) {
                negMap.put(p2, 1);
            } else {
                negMap.put(p2, negMap.get(p2) + 1);
            }
        }
        int count = 0;
        for (int c : posMap.values()) {
            count += c * (c - 1) / 2;
        }
        for (int c : negMap.values()) {
            count += c * (c - 1) / 2;
        }
        return count;
    }
}
//    public int countAttackingBishopPairs(Point[] points, int M) {
//        Map<Integer, Map<Integer, Integer>> posMap = new HashMap<>();
//        Map<Integer, Map<Integer, Integer>> negMap = new HashMap<>();
//        for(Point point : points) {
//            int posBoundaryX = point.x - Math.min(point.x, point.y);
//            int posBoundaryY = point.y - Math.min(point.x, point.y);
//            int negBoundaryX = point.x - Math.min(point.x, M - point.y);
//            int negBoundaryY = point.y + Math.min(point.x, M - point.y);
//
//            if(!posMap.containsKey(posBoundaryX)) {
//                posMap.put(posBoundaryX, new HashMap<>());
//            }
//            if(!posMap.get(posBoundaryX).containsKey(posBoundaryY)) {
//                posMap.get(posBoundaryX).put(posBoundaryY, 0);
//            }
//            Map<Integer, Integer> map = posMap.get(posBoundaryX);
//            map.put(posBoundaryY, map.get(posBoundaryY) + 1);
//
//            if(!negMap.containsKey(negBoundaryX)) {
//                negMap.put(negBoundaryX, new HashMap<>());
//            }
//            if(!negMap.get(negBoundaryX).containsKey(negBoundaryY)) {
//                negMap.get(negBoundaryX).put(negBoundaryY, 0);
//            }
//            map = negMap.get(negBoundaryX);
//            map.put(negBoundaryY, map.get(negBoundaryY) + 1);
//        }
//        int count = 0;
//        for(Map<Integer, Integer> map : posMap.values()) {
//            for(int c : map.values()) {
//                count += c * (c - 1) / 2;
//            }
//        }
//        for(Map<Integer, Integer> map : negMap.values()) {
//            for(int c : map.values()) {
//                count += c * (c - 1) / 2;
//            }
//        }
//        return count;
//    }
//}
