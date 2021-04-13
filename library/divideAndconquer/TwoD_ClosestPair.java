package divideAndconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author lzhang
 * @since 11/12/19
 */

/*
Given a set of distinct points in the 2D plane, find a pair of points that has the minimum euclidean distance.

The following implementation does not take double type comparison precision issue into account.

Runtime: O(N * log N); Space: O(N)
 */

public class TwoD_ClosestPair {
    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }
    public static Point[] closestPairDistance(Point[] points) {
        Point[] xSorted = new Point[points.length];
        Point[] ySorted = new Point[points.length];

        for(int i = 0; i < points.length; i++) {
            xSorted[i] = new Point(points[i].x, points[i].y);
            ySorted[i] = new Point(points[i].x, points[i].y);
        }
        Arrays.sort(xSorted, Comparator.comparingInt(Point::getX));
        Arrays.sort(ySorted, Comparator.comparingInt(Point::getY));


        return closestPairDistanceDAC(xSorted, 0, points.length - 1, ySorted);
    }
    private static Point[] closestPairDistanceDAC(Point[] xSorted, int x1, int x2, Point[] ySorted) {
        if(x2 == x1) {
            return null;
        }
        Point[] ans = new Point[2];
        if(x2 - x1 == 1) {
            ans[0] = xSorted[x1];
            ans[1] = xSorted[x2];
            return ans;
        }

        int mid = x1 + (x2 - x1) / 2;
        Point[] leftClosestPair = closestPairDistanceDAC(xSorted, x1, mid, ySorted);
        Point[] rightClosestPair = closestPairDistanceDAC(xSorted, mid + 1, x2, ySorted);

        double ld = leftClosestPair != null ? getDistance(leftClosestPair[0], leftClosestPair[1]) : Double.MAX_VALUE;
        double rd = rightClosestPair != null ? getDistance(rightClosestPair[0], rightClosestPair[1]) : Double.MAX_VALUE;
        double delta = -1;
        if(ld <= rd) {
            delta = ld;
            ans = leftClosestPair;
        }
        else {
            delta = rd;
            ans = rightClosestPair;
        }

        int midX = xSorted[mid].x;

        List<Point> Sy = new ArrayList<>();
        for(int i = 0; i < ySorted.length; i++) {
            if(ySorted[i].x >= midX - delta && ySorted[i].x <= midX + delta) {
                Sy.add(ySorted[i]);
            }
        }

        double best = delta;
        for(int i = 0; i < Sy.size() - 1; i++) {
            for(int j = 1; j <= 7 && i + j < Sy.size(); j++) {
                double currD = getDistance(Sy.get(i), Sy.get(i + j));
                if(currD < best) {
                    best = currD;
                    ans[0] = Sy.get(i);
                    ans[1] = Sy.get(i + j);
                }
            }
        }

        return ans;
    }

    private static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Point p0 = new Point(1,1);
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(6, 1);
        Point p4 = new Point(-1, -6);
        Point p5 = new Point(-4, -3);
        Point[] points = new Point[6];
        points[0] = p0;
        points[1] = p1;
        points[2] = p2;
        points[3] = p3;
        points[4] = p4;
        points[5] = p5;

        Point[] closestPair = closestPairDistance(points);
        System.out.println();
    }
}
