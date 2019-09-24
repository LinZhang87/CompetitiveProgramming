package geometry;

import java.util.Objects;

/**
 * @author lzhang
 * @since 6/1/19
 */

public class Rectangle {
    private int leftX;
    private int topY;
    private int width;
    private int height;

    public Rectangle(int leftX, int topY, int width, int height) {
        this.leftX = leftX;
        this.topY = topY;
        this.width = width;
        this.height = height;
    }

    public int getLeftX() {
        return leftX;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public int getTopY() {
        return topY;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return leftX == rectangle.leftX &&
                topY == rectangle.topY &&
                width == rectangle.width &&
                height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftX, topY, width, height);
    }
}
