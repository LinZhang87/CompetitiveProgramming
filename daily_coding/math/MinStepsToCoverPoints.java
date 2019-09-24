package math;

/**
 * @author lzhang
 * @since 3/6/19
 */

public class MinStepsToCoverPoints {
    class Point {
        int x, y;
    }
    public int minSteps(Point[] points) {
        int sum = 0;
        if(points.length > 1) {
            for(int i = 1; i < points.length; i++) {
                Point p2 = points[i], p1 = points[i - 1];
                int dx = Math.abs(p2.x - p1.x), dy = Math.abs(p2.y - p1.y);
                sum += Math.max(dx, dy);
            }
        }
        return sum;
    }
}
