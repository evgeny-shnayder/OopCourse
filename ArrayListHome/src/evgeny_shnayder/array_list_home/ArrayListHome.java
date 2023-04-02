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
            System.out.println(getFileLines(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден. Введите правильное имя файла!");
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 48, 2, 4, 81, 28, 81, 4, 4, 5, 4));

        System.out.println(list);

        removeEvenNumbers(list);

        System.out.println(list);
        System.out.println(getListWithoutRepeats(list));
    }

    public static ArrayList<String> getFileLines(String fileName) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        return bufferedReader.lines().collect(Collectors.toCollection(ArrayList::new));
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
        return list.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }
}
