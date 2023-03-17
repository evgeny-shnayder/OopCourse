package evgeny_shnayder.list_main;

import evgeny_shnayder.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(25);

        list.addBeforeItems(18);
        list.addBeforeItems(356);
        list.addBeforeItems(35);
        list.addItem(3, 123);
        System.out.println(list.removeFirstItem());

        System.out.println(list);
        System.out.println("Размер списка: " + list.getCount());
        System.out.println("Значение первого элемента списка: " + list.getFirstValue());
        System.out.println("Значение узла по индексу: " + list.getOnIndexValue(2));
        System.out.println("Предыдущее значение по индексу после изменения: " + list.setValueOnIndex(2, 9));
        System.out.println("Значение удаленного элемента по индексу: " + list.removeItem(3));
        System.out.println("Размер списка: " + list.getCount());

        list.addItem(1, 31);

        System.out.println(list.removeByValueItem(32));
        System.out.println(list);
        System.out.println("Размер списка: " + list.getCount());

        list.revers();

        System.out.println(list);

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(list);

        System.out.println(list1);
    }
}