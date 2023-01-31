package Range_main;

import evgeny_shnayder.Range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(3.14, 5.21);

        double number = 4.98;
        String answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.rangeLength());
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);

        range.setFrom(3.0);
        range.setTo(5.21);

        answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.rangeLength());
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);

        Range range2 = new Range(3.0, 5.21);

        if (range.getCrossingRange(range2) != null) {
            Range mergeRange = range.getCrossingRange(range2);
            System.out.println("Интервал - пересечение: " + mergeRange.getFrom() + " " + mergeRange.getTo());
        } else {
            System.out.println("Пересечения числовых интервалов от " + range.getFrom() + " до " + range.getTo() + " и "
                    + range2.getFrom() + " до " + range2.getTo() + " нет");
        }

        for (Range e : range.getUnionRange(range2)) {
            System.out.println("Объединение числовых интервалов: " + e.getFrom() + " " + e.getTo());
        }

        if (range.getRangeDifference(range2) != null) {
            System.out.print("Разность числовых интервалов:");

            for (Range e : range.getRangeDifference(range2)) {
                System.out.print(" от " + e.getFrom() + " до " + e.getTo());
            }
        } else {
            System.out.println("Разность числовых интервалов равна нулю.");
        }
    }
}