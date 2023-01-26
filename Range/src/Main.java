import Range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(3.14, 5.21);

        double number = 4.98;
        String answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.rangeLength(range.getFrom(), range.getTo()));
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);

        range.setTo(4.9);
        range.setFrom(4.2);

        answer = range.isInside(number) ? "Да" : "Нет";

        System.out.println("Длина промежутка: " + range.rangeLength(range.getFrom(), range.getTo()));
        System.out.println("Число " + number + " принадлежит промежутку от " + range.getFrom() + " до " + range.getTo()
                + ": " + answer);
    }
}