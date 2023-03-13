package evgeny_shnayder.arrayl_ist_main;

import evgeny_shnayder.array_list.ArrayListHome;

public class Main {
    public static void main(String[] args) {
        ArrayListHome list = new ArrayListHome("file.txt");

        System.out.println("Список из содержимого файла: " + list);
        System.out.println("Список без повторов: " + list.getWithoutRepeatsList());

        list.convertToOddNumbersList();

        System.out.println("Удалены все четные числа из списка: " + list);
        System.out.println("Список без повторов: " + list.getWithoutRepeatsList());
    }
}
