package evgeny_shnayder.comparators;

import evgeny_shnayder.shapes.Shape;

import java.util.Comparator;

public class MaxAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return (int) (o1.getArea() - o2.getArea());
    }
}
