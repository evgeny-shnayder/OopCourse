package evgeny_shnayder.shapes_main;

import evgeny_shnayder.shapes_comparators.AreaComparator;
import evgeny_shnayder.shapes_comparators.PerimeterComparator;
import evgeny_shnayder.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(3.5);

        square.setWidth(4);

        System.out.println(square);

        Triangle triangle = new Triangle(1.1, 5.1, 5.1, 1.1, 1.1, 5.1);

        triangle.setX1(1);
        triangle.setY1(1);
        triangle.setX2(5);
        triangle.setY2(2);
        triangle.setX3(6);
        triangle.setY3(5);

        System.out.println("Треугольник с координатами точек: Х1 - " + triangle.getX1() + ", Y1 - "
                + triangle.getY1() + ", X2 - " + triangle.getX2() + ", Y2 - " + triangle.getY2()
                + ", X3 - " + triangle.getX3() + ", Y3 - " + triangle.getY3() + ".");

        Rectangle rectangle = new Rectangle(2.5, 3.5);

        rectangle.setHeight(3.9);
        rectangle.setWidth(2.4);

        System.out.println(rectangle);

        Circle circle = new Circle(2.5);

        circle.setRadius(3);

        Shape[] shapes = {
                new Circle(3),
                new Square(3),
                new Rectangle(2.4, 3.9),
                new Triangle(1, 1, 1, 5.1, 6, 2),
                new Circle(4.1),
                new Square(4)
        };

        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println(shape + " шириной - " + shape.getWidth());
            System.out.println(shape + " высотой - " + shape.getHeight());
            System.out.println(shape + " площадь - " + shape.getArea());
            System.out.println(shape + " периметр - " + shape.getPerimeter());
        }

        System.out.println("Фигура с максимальной площадью: " + getMaxAreaShape(shapes));

        System.out.println("Вторая по величине периметра фигура: " + getSecondLargestPerimeterShape(shapes));

        for (Shape shape : shapes) {
            System.out.println(shape + " hash: " + shape.hashCode());
        }

        for (Shape shape : shapes) {
            System.out.println(circle + (circle.equals(shape) ? " равен " : " не равен ") + shape);
        }
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondLargestPerimeterShape(Shape[] shapes) {
        if (shapes.length < 2) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }
}
