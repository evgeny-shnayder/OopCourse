package evgeny_shnayder.shape_main;

//import evgeny_shnayder.Shape.Shape;
import evgeny_shnayder.Shape.Circle;
import evgeny_shnayder.Shape.Rectangle;
import evgeny_shnayder.Shape.Square;
import evgeny_shnayder.Shape.Triangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Square square = new Square (scanner.nextDouble());

        System.out.println("Длина квадрата - " + square.getHeight());
        System.out.println("Высота квадрата - " + square.getWidth());
        System.out.println("Площадь квадрата - " + square.getArea());
        System.out.println("Периметр квадрата - " + square.getPerimeter());

        Triangle triangle = new Triangle(1.1,5.1,5.1, 1.1, 1.1, 5.1);

        System.out.println("Длина треугольника - " + triangle.getHeight());
        System.out.println("Высота треугольника - " + triangle.getWidth());
        System.out.println("Площадь треугольника - " + triangle.getArea());
        System.out.println("Периметр треугольника - " + triangle.getPerimeter());

        Rectangle rectangle = new Rectangle(2.5, 3.5);

        System.out.println("Длина прямоугольника - " + rectangle.getHeight());
        System.out.println("Высота прямоугольника - " + rectangle.getWidth());
        System.out.println("Площадь прямоугольника - " + rectangle.getArea());
        System.out.println("Периметр прямоугольника - " + rectangle.getPerimeter());

        Circle circle = new Circle(2.5);

        System.out.println("Ширина круга - " + circle.getHeight());
        System.out.println("Высота круга - " + circle.getWidth());
        System.out.println("Площадь круга - " + circle.getArea());
        System.out.println("Периметр круга - " + circle.getPerimeter());
    }
}