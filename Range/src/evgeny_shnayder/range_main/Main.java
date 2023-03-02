package evgeny_shnayder.range_main;

import evgeny_shnayder.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3.14, 5.21);

        double number = 4.98;
        String answer = range1.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина интервала: " + range1.getLength());
        System.out.println("Число " + number + " принадлежит интервалу " + range1 + ": " + answer);

        range1.setFrom(1.0);
        range1.setTo(5.21);

        answer = range1.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина интервала: " + range1.getLength());
        System.out.println("Число " + number + " принадлежит интервалу " + range1 + ": " + answer);

        Range range2 = new Range(2.0, 3.21);

        Range intersection = range1.getIntersection(range2);

        if (intersection != null) {
            System.out.println("Интервал - пересечение интервалов: " + intersection);
        } else {
            System.out.println("Пересечения интервалов " + range1 + " и " + range2 + " нет");
        }

        System.out.println("Объединение числовых интервалов: " + Arrays.toString(range1.getUnion(range2)));

        System.out.println();

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.print("Разность числовых интервалов равна нулю");
        } else {
            System.out.print("Разность числовых интервалов:" + Arrays.toString(difference));
        }
    }
}
