package evgeny_shnayder.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    public void setWidth(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Квадрат стороной: " + sideLength;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) object;

        return square.sideLength == sideLength;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(sideLength);
    }
}
