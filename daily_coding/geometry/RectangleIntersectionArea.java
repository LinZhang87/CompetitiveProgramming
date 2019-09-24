package geometry;

/**
 * @author lzhang
 * @since 5/30/19
 */

public class RectangleIntersectionArea {
    static class Rectangle {
        int topLeft_X, topLeft_Y;
        int width, height;
        Rectangle(int x, int y, int width, int height) {
            this.topLeft_X = x;
            this.topLeft_Y = y;
            this.width = width;
            this.height = height;
        }
    }
    public static int rectangleIntersection(Rectangle r1, Rectangle r2) {
        int left = Math.max(r1.topLeft_X, r2.topLeft_X);
        int right = Math.min(r1.topLeft_X + r1.width, r2.topLeft_X + r2.width);
        if(left > right) {
            return 0;
        }
        int top = Math.min(r1.topLeft_Y, r2.topLeft_Y);
        int bottom = Math.max(r1.topLeft_Y - r1.height, r2.topLeft_Y - r2.height);
        if(bottom > top) {
            return 0;
        }
        return (right - left) * (top - bottom);
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(1, 4, 3, 3);
        Rectangle r2 = new Rectangle(0, 5, 4, 3);
        System.out.println(rectangleIntersection(r1, r2));
    }
}
