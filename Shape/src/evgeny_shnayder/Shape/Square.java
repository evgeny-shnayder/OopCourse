package evgeny_shnayder.Shape;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
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
}
