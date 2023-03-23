package evgeny_shnayder.array_list;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя файла:");
        String fileName = scanner.nextLine();

        try {
            System.out.println(recordFromFile(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден. Введите правильное имя файла!");
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 48, 2, 4, 81, 28, 81, 4, 4, 5, 4));

        System.out.println(list);

        removeEvenNumbers(list);

        System.out.println(list);
        System.out.println(getListWithoutRepeats(list));
    }

    public static ArrayList<String> recordFromFile(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new FileInputStream(fileName));
        if (!file.hasNext()) {
            throw new RuntimeException("Файл " + fileName + " пустой.");
        } else {
            ArrayList<String> list = new ArrayList<>();

            while (file.hasNext()) {
                list.add(file.next());
            }

            return list;
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
        ArrayList<Integer> listWithoutRepeats = new ArrayList<>(list);

        for (int i = 0; i < listWithoutRepeats.size(); i++) {
            while (listWithoutRepeats.lastIndexOf(listWithoutRepeats.get(i)) != i) {
                listWithoutRepeats.remove(i);
            }
        }

        return listWithoutRepeats;
    }
}
