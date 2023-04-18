package evgeny_shnayder.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayListHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя файла:");
        String fileName = scanner.nextLine();

        try {
            System.out.println("Строки из файла: " + getFileLines(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 48, 2, 4, 81, 28, 81, 4, 4, 5, 4));

        System.out.println("Список: " + list);

        removeEvenNumbers(list);

        System.out.println("Список без четных чисел: " + list);
        System.out.println("Список без повторов: " + getListWithoutRepeats(list));
    }

    public static ArrayList<String> getFileLines(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            return bufferedReader.lines().collect(Collectors.toCollection(ArrayList::new));
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutRepeats(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutRepeats = new ArrayList<>(list.size());

        for (Integer integer : list) {
            if (!listWithoutRepeats.contains(integer)) {
                listWithoutRepeats.add(integer);
            }
        }

        return listWithoutRepeats;
    }
}
