package evgeny_shnayder.list_main;

import evgeny_shnayder.list.SinglyLinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(25);

        list1.addFirst(18);
        list1.addFirst(356);
        list1.addFirst(35);
        list1.addByIndex(2, 123);
        System.out.println(list1);
        System.out.println(list1.removeFirst());

        System.out.println(list1);
        System.out.println("Размер списка: " + list1.getCount());
        System.out.println("Значение первого элемента списка: " + list1.getFirst());
        System.out.println("Значение узла по индексу: " + list1.getByIndex(1));
        System.out.println("Предыдущее значение по индексу после изменения: " + list1.setByIndex(2, 9));
        System.out.println("Значение удаленного элемента по индексу: " + list1.removeByIndex(2));
        System.out.println("список 1: " + list1);
        System.out.println("Размер списка: " + list1.getCount());

        list1.addByIndex(1, null);

        System.out.println(list1.removeByData(12));
        System.out.println(list1);
        System.out.println("Размер списка: " + list1.getCount());

        list1.reverse();

        System.out.println(list1);

        SinglyLinkedList<String> list3 = new SinglyLinkedList<>();

        list3.addFirst("hello");
        list3.addFirst(null);
        list3.addFirst(null);
        list3.addFirst("cool");

        System.out.println(list3.removeByData(null));

        System.out.println("список 3: " + list3);
        System.out.println("список 1: " + list1);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>(list1);

        System.out.println("список 2: " + list2);

        list1.setByIndex(2, 35);
        list1.setByIndex(0, 89);

        System.out.println("список 1: " + list1);
        System.out.println("список 2: " + list2);
        System.out.println("размер списка 2: " + list2.getCount());

        SinglyLinkedList<Integer> list4 = new SinglyLinkedList<>();

        System.out.println("список 4: " + list4);
        list4.reverse();
    }
}
