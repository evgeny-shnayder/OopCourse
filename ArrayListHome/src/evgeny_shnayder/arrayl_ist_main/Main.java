package evgeny_shnayder.arrayl_ist_main;

import evgeny_shnayder.array_list.ArrayListHome;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayListHome list = new ArrayListHome("file.txt");

        System.out.println("Список из содержимого файла: " + list);

        ArrayList<Integer> list1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }

        list1.add(1);
        list1.add(2, 1);

        System.out.println("Список: " + list1);

        ArrayListHome.convertToOddNumbersList(list1);

        System.out.println("Удалены все четные числа из списка: " + list1);
        System.out.println("Список без повторов: " + ArrayListHome.getWithoutRepeatsList(list1));
    }
}
