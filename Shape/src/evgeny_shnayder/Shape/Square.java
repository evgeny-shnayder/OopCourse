package evgeny_shnayder.Shape;

public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double getWidth() {
        return length;
    }

    public double getHeight() {
        return length;
    }

    public double getArea() {
        return Math.pow(length, 2.0);
    }

    public double getPerimeter() {
        return length + length + length + length;
    }
}
