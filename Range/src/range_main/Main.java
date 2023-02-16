package range_main;

import evgeny_shnayder.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(3.14, 5.21);

        double number = 4.98;
        String answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.getLength());
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);

        range.setFrom(2.0);
        range.setTo(4.21);

        answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.getLength());
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);

        Range range2 = new Range(4.21, 6.0);

        if (range.getIntersection(range2) != null) {
            Range mergeRange = range.getIntersection(range2);
            System.out.println("Интервал - пересечение: " + mergeRange.getFrom() + " " + mergeRange.getTo());
        } else {
            System.out.println("Пересечения числовых интервалов от " + range.getFrom() + " до " + range.getTo() + " и "
                    + range2.getFrom() + " до " + range2.getTo() + " нет");
        }

        System.out.print("Объединение числовых интервалов:");

        for (Range e : range.getUnion(range2)) {
            System.out.print(" " + e.getFrom() + " " + e.getTo());
        }

        System.out.println();

        System.out.print("Разность числовых интервалов:");

        for (Range e : range.getDifference(range2)) {
            System.out.print(" от " + e.getFrom() + " до " + e.getTo());
        }
    }
}
