package evgeny_shnayder.shape_main;

import evgeny_shnayder.Shape.Circle;
import evgeny_shnayder.Shape.Rectangle;
import evgeny_shnayder.Shape.Square;
import evgeny_shnayder.Shape.Triangle;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(3.5);

        square.setWidth(4);

        System.out.println("Квадрат со сторонами - " + square.getHeight());
        System.out.println("Высота квадрата - " + square.getHeight());
        System.out.println("Ширина квадрата - " + square.getWidth());
        System.out.println("Площадь квадрата - " + square.getArea());
        System.out.println("Периметр квадрата - " + square.getPerimeter());

        Triangle triangle = new Triangle(1.1, 5.1, 5.1, 1.1, 1.1, 5.1);

        triangle.setCoordinateX1(1);
        triangle.setCoordinateX2(5);
        triangle.setCoordinateX3(5);
        triangle.setCoordinateY1(1);
        triangle.setCoordinateY2(1);
        triangle.setCoordinateY3(5);

        System.out.println("Треугольник с координатами точек: Х1 - " + triangle.getCoordinateX1() + ", Y1 - " + triangle.getCoordinateY1()
                + ", X2 - " + triangle.getCoordinateX2() + ", Y2 - " + triangle.getCoordinateY2() + ", X3 - " + triangle.getCoordinateX3()
                + ", Y3 - " + triangle.getCoordinateY3() + ".");
        System.out.println("Длина треугольника - " + triangle.getHeight());
        System.out.println("Высота треугольника - " + triangle.getWidth());
        System.out.println("Площадь треугольника - " + triangle.getArea());
        System.out.println("Периметр треугольника - " + triangle.getPerimeter());

        Rectangle rectangle = new Rectangle(2.5, 3.5);

        rectangle.setHeight(3.9);
        rectangle.setWidth(2.4);

        System.out.println("Прямоугольник со сторонами: высота - " + rectangle.getHeight() + " ширина - " + rectangle.getWidth());
        System.out.println("Длина прямоугольника - " + rectangle.getHeight());
        System.out.println("Высота прямоугольника - " + rectangle.getWidth());
        System.out.println("Площадь прямоугольника - " + rectangle.getArea());
        System.out.println("Периметр прямоугольника - " + rectangle.getPerimeter());

        Circle circle = new Circle(2.5);

        circle.setRadius(2);

        System.out.println("Круг радиусом - " + circle.getRadius());
        System.out.println("Ширина круга - " + circle.getHeight());
        System.out.println("Высота круга - " + circle.getWidth());
        System.out.println("Площадь круга - " + circle.getArea());
        System.out.println("Периметр круга - " + circle.getPerimeter());
    }
}