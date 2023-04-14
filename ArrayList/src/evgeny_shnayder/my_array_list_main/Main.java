package evgeny_shnayder.my_array_list_main;

import evgeny_shnayder.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println(list.add(25));

        list.add(14);
        list.add(12);
        list.add(428);
        list.add(0, 5);

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Список: " + list);

        Object[] array = list.toArray();

        System.out.println("Массив: " + Arrays.toString(array));

        list.add(156);
        System.out.println("Список: " + list);
        System.out.println("Размер списка: " + list.getSize());
        System.out.println("Массив: " + Arrays.toString(array));

        list.remove(4);
        System.out.println("Список: " + list);

        Integer[] ar = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(5, 45, 28, 14));

        list.addAll(list1);

        System.out.println("Список после добавления коллекции: " + list);
        System.out.println("Массив на основе списка: " + Arrays.toString(list.toArray(ar)));
        System.out.println("Исходный массив: " + Arrays.toString(ar));
        System.out.println("Список содержит элемент: " + list.contains(5));
        System.out.println("Размер списка: " + list.getSize());

        list.addAll(2, list1);
        System.out.println("Список после добавления коллекции: " + list);
        System.out.println("Размер списка: " + list.getSize());

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(4, 5, 25));
        ArrayList<Integer> list4 = new ArrayList<>(Arrays.asList(25, 156, 44));

        System.out.println(list.removeAll(list3));
        System.out.println("Список после удаления коллекции: " + list);
        System.out.println(list.containsAll(list4));
        System.out.println(list.retainAll(list4));
        System.out.println("Список после удаления коллекции: " + list);
        System.out.println("Размер списка: " + list.getSize());

        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("true");
        list2.add("false");
        list2.add("null");

        System.out.println("Список: " + list2);
        System.out.println("Список содержит элемент: " + list2.contains("null"));
        System.out.println("Размер списка: " + list2.getSize());



//        System.out.println(list1.add(25));
//        list1.add(0,null);
        System.out.println(list1.add(15));
        list1.add(4, 5);
        System.out.println(list1);
        System.out.println(list1.contains(null));

        ArrayList<Integer> list5 = new ArrayList<>(Arrays.asList(12, 46));

        System.out.println("Список: " + list1);
        System.out.println(list1.retainAll(list5));
        System.out.println("Список: " + list1);

        System.out.println("Массив на основе списка: " + Arrays.toString(list1.toArray(ar)));

        System.out.println("Исходный массив: " + Arrays.toString(ar));
    }
}