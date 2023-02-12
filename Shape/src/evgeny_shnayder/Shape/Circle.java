package evgeny_shnayder.Shape;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Круг радиусом: " + radius;
    }

    @Override
    public boolean equals(Object circle) {
        if (circle == this) {
            return true;
        }

        if (circle == null || circle.getClass() != getClass()) {
            return false;
        }

        Circle testCircle = (Circle) circle;

        return radius == testCircle.radius;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}
