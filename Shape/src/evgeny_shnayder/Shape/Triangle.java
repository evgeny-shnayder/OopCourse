package evgeny_shnayder.Shape;

public class Triangle implements Shape {
    private double coordinateX1;
    private double coordinateX2;
    private double coordinateX3;
    private double coordinateY1;
    private double coordinateY2;
    private double coordinateY3;

    public Triangle(double coordinateX1, double coordinateX2, double coordinateX3, double coordinateY1, double coordinateY2,
                    double coordinateY3) {
        this.coordinateX1 = coordinateX1;
        this.coordinateX2 = coordinateX2;
        this.coordinateX3 = coordinateX3;
        this.coordinateY1 = coordinateY1;
        this.coordinateY2 = coordinateY2;
        this.coordinateY3 = coordinateY3;
    }

    public double getCoordinateX1() {
        return coordinateX1;
    }

    public double getCoordinateX2() {
        return coordinateX2;
    }

    public double getCoordinateX3() {
        return coordinateX3;
    }

    public double getCoordinateY1() {
        return coordinateY1;
    }

    public double getCoordinateY2() {
        return coordinateY2;
    }

    public double getCoordinateY3() {
        return coordinateY3;
    }

    public void setCoordinateX1(double coordinateX1) {
        this.coordinateX1 = coordinateX1;
    }

    public void setCoordinateX2(double coordinateX2) {
        this.coordinateX2 = coordinateX2;
    }

    public void setCoordinateX3(double coordinateX3) {
        this.coordinateX3 = coordinateX3;
    }

    public void setCoordinateY1(double coordinateY1) {
        this.coordinateY1 = coordinateY1;
    }

    public void setCoordinateY2(double coordinateY2) {
        this.coordinateY2 = coordinateY2;
    }

    public void setCoordinateY3(double coordinateY3) {
        this.coordinateY3 = coordinateY3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(coordinateX1, coordinateX2), coordinateX3) - Math.min(Math.min(coordinateX1, coordinateX2), coordinateX3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(coordinateY1, coordinateY2), coordinateY3) - Math.min(Math.min(coordinateY1, coordinateY2), coordinateY3);
    }

    @Override
    public double getArea() {
        double sideLength1 = Math.sqrt(Math.pow(coordinateX2 - coordinateX1, 2) + Math.pow(coordinateY2 - coordinateY1, 2));
        double sideLength2 = Math.sqrt(Math.pow(coordinateX3 - coordinateX2, 2) + Math.pow(coordinateY3 - coordinateY2, 2));
        double sideLength3 = Math.sqrt(Math.pow(coordinateX3 - coordinateX1, 2) + Math.pow(coordinateY3 - coordinateY1, 2));
        double semiPerimeter = (sideLength1 + sideLength2 + sideLength3) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - sideLength1) * (semiPerimeter - sideLength2)
                * (semiPerimeter - sideLength3));
    }

    @Override
    public double getPerimeter() {
        double sideLength1 = Math.sqrt(Math.pow(coordinateX2 - coordinateX1, 2) + Math.pow(coordinateY2 - coordinateY1, 2));
        double sideLength2 = Math.sqrt(Math.pow(coordinateX3 - coordinateX2, 2) + Math.pow(coordinateY3 - coordinateY2, 2));
        double sideLength3 = Math.sqrt(Math.pow(coordinateX3 - coordinateX1, 2) + Math.pow(coordinateY3 - coordinateY1, 2));

        return sideLength1 + sideLength2 + sideLength3;
    }
}
