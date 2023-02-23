package range_main;

import evgeny_shnayder.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3.14, 5.21);

        double number = 4.98;
        String answer = range1.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина интервала: " + range1.getLength());
        System.out.println("Число " + number + " принадлежит интервалу " + range1 + ": " + answer);

        range1.setFrom(2.0);
        range1.setTo(4.21);

        answer = range1.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина интервала: " + range1.getLength());
        System.out.println("Число " + number + " принадлежит интервалу " + range1 + ": " + answer);

        Range range2 = new Range(2.0, 3.21);

        Range intersection = range1.getIntersection(range2);

        if (intersection != null) {
            System.out.println("Интервал - пересечиние интервалов: " + intersection);
        } else {
            System.out.println("Пересечения интервалов " + range1 + " и " + range2 + " нет");
        }

        System.out.print("Объединение числовых интервалов: ");

        for (Range range : range1.getUnion(range2)) {
            System.out.print(range);
        }

        System.out.println();

        Range[] difference = range1.getDifference(range2);

        if (difference.length != 0) {
            System.out.print("Разность числовых интервалов:");

            for (Range range : difference) {
                System.out.print(" от " + range.getFrom() + " до " + range.getTo());
            }
        } else {
            System.out.print("Разность числовых интервалов равна нулю");
        }
    }
}
