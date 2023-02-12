package evgeny_shnayder.Shape;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }

    @Override
    public double getArea() {
        return Math.pow(width, 2.0);
    }

    @Override
    public double getPerimeter() {
        return width * 4;
    }

    @Override
    public String toString() {
        return "Квадрат стороной: " + getWidth();
    }

    @Override
    public boolean equals(Object square) {
        if (square == this) {
            return true;
        }

        if (square == null || square.getClass() != getClass()) {
            return false;
        }

        Square testSquare = (Square) square;

        return testSquare.width == width;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(width);
    }
}
