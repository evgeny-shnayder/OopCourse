package evgeny_shnayder.shapes_main;

import evgeny_shnayder.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(3.5);

        square.setWidth(4);

        System.out.println(square);
        System.out.println("Высота квадрата - " + square.getHeight());
        System.out.println("Ширина квадрата - " + square.getWidth());
        System.out.println("Площадь квадрата - " + square.getArea());
        System.out.println("Периметр квадрата - " + square.getPerimeter());

        Triangle triangle = new Triangle(1.1, 5.1, 5.1, 1.1, 1.1, 5.1);

        triangle.setX1(1);
        triangle.setX2(5);
        triangle.setX3(5);
        triangle.setY1(1);
        triangle.setY2(1);
        triangle.setY3(5);

        System.out.println(triangle);
        System.out.println("Треугольник с координатами точек: Х1 - " + triangle.getX1() + ", Y1 - "
                + triangle.getY1() + ", X2 - " + triangle.getX2() + ", Y2 - " + triangle.getY2()
                + ", X3 - " + triangle.getX3() + ", Y3 - " + triangle.getY3() + ".");
        System.out.println("Высота треугольника - " + triangle.getHeight());
        System.out.println("Ширина треугольника - " + triangle.getWidth());
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

        circle.setRadius(3);

        System.out.println("Круг радиусом - " + circle.getRadius());
        System.out.println("Ширина круга - " + circle.getHeight());
        System.out.println("Высота круга - " + circle.getWidth());
        System.out.println("Площадь круга - " + circle.getArea());
        System.out.println("Периметр круга - " + circle.getPerimeter());

        Shape[] shapes = {
                new Circle(3),
                new Square(3),
                new Rectangle(2.4, 3.9),
                new Triangle(1, 5, 5, 1, 1, 5),
                new Circle(4.1),
                new Square(4)
        };

        System.out.println("Максимальная площадь фигуры: " + sortMaxAreaShape(shapes));
        System.out.println("Вторая по величине периметра фигура: " + sortMaxPerimeterShape(shapes));

        for (Shape e : shapes) {
            System.out.println(e + " hash: " + e.hashCode());
        }

        System.out.println(circle + (circle.equals(shapes[2]) ? " равен " : " не равен ") + shapes[2]);
        System.out.println(triangle + (triangle.equals(shapes[4]) ? " равен " : " не равен ") + shapes[4]);
        System.out.println(square + (square.equals(shapes[3]) ? " равен " : " не равен ") + shapes[3]);
        System.out.println(rectangle + (rectangle.equals(shapes[2]) ? " равен " : " не равен ") + shapes[2]);
    }

    static Shape sortMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparing(Shape::getArea).reversed());
        return shapes[0];
    }

    static Shape sortMaxPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparing(Shape::getPerimeter).reversed());
        return shapes[1];
    }
}
