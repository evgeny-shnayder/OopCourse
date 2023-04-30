package evgeny_shnayder.hash_table_main;

import evgeny_shnayder.hash_table.HashTable;
import evgeny_shnayder.hash_table_person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();

        table1.add(28);
        table1.add(40);
        table1.add(1);
        table1.add(0);
        table1.add(4);
        table1.add(4);

        System.out.println("Таблица 1: " + table1);
        System.out.println("Размер таблицы 1: " + table1.size());

        table1.remove(0);

        System.out.println("Таблица 1: " + table1);
        System.out.println("Количество элементов таблицы 1: " + table1.size());

        table1.remove(40);

        List<Integer> list1 = Arrays.asList(4, 45, 125, 38);

        List<Integer> list2 = new ArrayList<>();

        System.out.println(table1.addAll(list1));
        System.out.println("Таблица 1 после дабавления списка 1: " + table1);
        System.out.println("Количество элементов таблицы 1: " + table1.size());
        System.out.println(table1.addAll(list2));
        System.out.println("Таблица 1 после дабавления списка 2: " + table1);
        System.out.println(Arrays.toString(table1.toArray()));

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(Arrays.toString(table1.toArray(array)));

        List<Integer> list = Arrays.asList(28, 8, 18);

        System.out.println(table1.containsAll(list));

        System.out.println(table1.removeAll(list));

        table1.clear();

        System.out.println("Таблица 1: " + table1);

        table1.add(28);
        table1.add(18);
        table1.add(8);
        table1.add(12);

        System.out.println(table1.retainAll(list));

        System.out.println("Таблица 1: " + table1);

        HashTable<String> table2 = new HashTable<>();
        System.out.println(table2);

        table2.add("jordan");
        table2.add("hello");
        table2.add("hello");
        table2.add("helo");

        System.out.println(table2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 1, 15, 45, 48));

        System.out.println(list3.removeAll(list));
        System.out.println(list3);

        table1.add(1);

        Iterator<Integer> iterator = table1.iterator();

        while (iterator.hasNext()) {
                    System.out.println(iterator.next());
        }

        Person person1 = new Person("Petr", 18);
        Person person2 = new Person("Ivan", 22);
        Person person3 = new Person("Vladimir", 48);

        HashTable<Person> table3 = new HashTable<>(3);

        table3.add(person1);
        table3.add(person2);
        table3.add(person3);

        System.out.println(table3);

        Iterator<Person> iterator2 = table3.iterator();

        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}