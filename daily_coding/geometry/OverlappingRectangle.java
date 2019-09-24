package geometry;

import java.util.Comparator;
import java.util.List;

/**
 * @author lzhang
 * @since 6/1/19
 */

/*
This problem was asked by Google.

You are given given a list of rectangles represented by min and max x- and y-coordinates. Compute whether or not a pair of rectangles overlap each other.
If one rectangle completely covers another, it is considered overlapping.

For example, given the following rectangles:

{
    "top_left": (1, 4),
    "dimensions": (3, 3) # width, height
},
{
    "top_left": (-1, 3),
    "dimensions": (2, 1)
},
{
    "top_left": (0, 5),
    "dimensions": (4, 4)
}
return true as the first and third rectangle overlap each other.

O(N^2) solution is pretty straightforward, can you do better than this runtime?

Reference: https://stackoverflow.com/questions/30307168/given-a-set-of-rectangles-do-any-overlap

 */

public class OverlappingRectangle {
    public static boolean hasOverlappingRectangle(List<Rectangle> rectangles) {
        rectangles.sort(Comparator.comparingInt(Rectangle::getLeftX));


        return false;
    }
    private static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        int r1_left = r1.getLeftX(), r1_right = r1_left + r1.getWidth();
        int r2_left = r2.getLeftX(), r2_right = r2_left + r2.getWidth();
        int r1_top = r1.getTopY(), r1_bottom = r1_top - r1.getHeight();
        int r2_top = r2.getTopY(), r2_bottom = r2_top - r2.getHeight();

        return r1_left <= r2_left && r1_top >= r2_top && r1_right >= r2_right && r1_bottom <= r2_bottom
                || r1_left >= r2_left && r1_top <= r2_top && r1_right <= r2_right && r1_bottom >= r2_bottom;
    }
    public static void main(String[] args) {

    }
}
