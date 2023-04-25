package evgeny_shnayder.array_list_main;

import evgeny_shnayder.array_list.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();

        System.out.println(list1.add(25));
        System.out.println(list1.add(25));

        list1.add(14);
        list1.add(12);
        list1.add(428);
        list1.add(0, 5);

        Iterator<Integer> iterator = list1.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Список 1: " + list1);

        Object[] array1 = list1.toArray();

        System.out.println("Массив: " + Arrays.toString(array1));

        list1.add(156);
        System.out.println("Список 1: " + list1);
        System.out.println("Размер списка 1: " + list1.getSize());
        System.out.println("Массив: " + Arrays.toString(array1));

        list1.remove(0);
        System.out.println("Список 1: " + list1);

        Integer[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        java.util.ArrayList<Integer> list2 = new java.util.ArrayList<>(Arrays.asList(5, 45, 28, 14));

        list1.addAll(list2);

        System.out.println("Список 1 после добавления списка 2: " + list1);
        System.out.println("Массив на основе списка 1: " + Arrays.toString(list1.toArray(array2)));
        System.out.println("Исходный массив: " + Arrays.toString(array2));
        System.out.println("Список 1 содержит элемент: " + list1.contains(null));
        System.out.println("Размер списка 1: " + list1.getSize());

        list1.addAll(2, list2);
        System.out.println("Список 1 после добавления списка 2: " + list1);

        list1.add(0, 125);
        System.out.println("Список 1: " + list1);
        System.out.println("Размер списка 1: " + list1.getSize());

        java.util.ArrayList<Integer> list4 = new java.util.ArrayList<>(Arrays.asList(4, 5, 25));
        java.util.ArrayList<Integer> list5 = new java.util.ArrayList<>(Arrays.asList(15, 156, 45));

        System.out.println(list1.removeAll(list4));
        System.out.println("Список 1 после удаления списка 4: " + list1);
        System.out.println("Список 1 содержит список 5: " + list1.containsAll(list5));
        System.out.println(list1.retainAll(list5));
        System.out.println("Список 1 после удаления списка 5: " + list1);
        System.out.println("Размер списка: " + list1.getSize());

        ArrayList<String> list3 = new ArrayList<>();

        list3.add("true");
        list3.add("false");
        list3.add("null");

        System.out.println("Список 3: " + list3);
        System.out.println("Список содержит элемент: " + list3.contains("null"));
        System.out.println("Размер списка 2: " + list3.getSize());
        System.out.println(list2.add(15));
        list2.add(4, 5);
        System.out.println("Список 2: " + list2);
        System.out.println("Список содержит элемент: " + list2.contains(null));

        java.util.ArrayList<Integer> list6 = new java.util.ArrayList<>(Arrays.asList(12, 46));

        System.out.println("Список: " + list2);
        System.out.println(list2.retainAll(list6));
        System.out.println("Список: " + list2);

        System.out.println("Массив на основе списка: " + Arrays.toString(list2.toArray(array2)));
        System.out.println("Исходный массив: " + Arrays.toString(array2));
    }
}