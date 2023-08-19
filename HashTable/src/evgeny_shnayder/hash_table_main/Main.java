package evgeny_shnayder.hash_table_main;

import evgeny_shnayder.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();

        table1.add(28);
        System.out.println("Размер таблицы 1: " + table1.size());
        table1.add(40);
        System.out.println("Размер таблицы 1: " + table1.size());
        table1.add(1);
        table1.add(0);
        table1.add(4);
        table1.add(4);
        table1.add(null);

        System.out.println("Таблица 1: " + table1);
        System.out.println("Размер таблицы 1: " + table1.size());

        table1.remove(1);
        table1.remove(1);

        System.out.println("Таблица 1: " + table1);
        System.out.println("Количество элементов таблицы 1: " + table1.size());

        table1.remove(40);
        System.out.println("Размер таблицы 1: " + table1.size());

        List<Integer> list1 = Arrays.asList(4, 45, 125, 38);

        List<Integer> list2 = new ArrayList<>();

        System.out.println(table1.addAll(list1));
        System.out.println("Таблица 1 после дабавления списка 1: " + table1);
        System.out.println("Количество элементов таблицы 1: " + table1.size());
        System.out.println(table1.addAll(list2));
        System.out.println("Таблица 1 после дабавления списка 2: " + table1);
        System.out.println("Размер таблицы 1: " + table1.size());
        System.out.println("Массив из списка 1: " + Arrays.toString(table1.toArray()));

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        System.out.println("Список 1 в массиве: " + Arrays.toString(table1.toArray(array)));
        System.out.println("Массив: " + Arrays.toString(array));

        List<Integer> list = Arrays.asList(28, 38, 4);

        System.out.println(table1.containsAll(list));
        System.out.println("Таблица 1: " + table1);
        System.out.println("Размер таблицы 1: " + table1.size());
        System.out.println(table1.removeAll(list));
        System.out.println("Размер таблицы 1: " + table1.size());
        System.out.println("Таблица 1: " + table1);
        table1.clear();
        System.out.println("Размер таблицы 1: " + table1.size());
        System.out.println("Таблица 1: " + table1);

        table1.add(28);
        table1.add(18);
        table1.add(8);
        table1.add(12);

        System.out.println("Таблица 11: " + table1);
        System.out.println(table1.retainAll(list));
        System.out.println("Размер таблицы 1: " + table1.size());

        System.out.println("Таблица 1: " + table1);

        HashTable<String> table2 = new HashTable<>();
        System.out.println("Таблица 2: " + table2);

        table2.add("jordan");
        table2.add("hello");
        table2.add("hello");
        table2.add("helo");

        System.out.println("Таблица 2: " + table2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 1, 15, 45, 48));

        System.out.println(list3.removeAll(list));
        System.out.println(list3);

        table1.add(1);
        table1.add(9);
        table1.add(10);
        table1.add(null);
        System.out.println("Таблица 1: " + table1);

        Iterator<Integer> iterator = table1.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Размер таблица 1: " + table1.size());
    }
}
