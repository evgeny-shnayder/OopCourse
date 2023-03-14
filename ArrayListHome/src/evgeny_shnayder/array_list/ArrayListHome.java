package evgeny_shnayder.array_list;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome {
    private List<String> list;

    public ArrayListHome(String fileName) {
        try (Scanner file = new Scanner(new FileInputStream(fileName))) {
            if (file.hasNext()) {
                list = new ArrayList<>();

                while (file.hasNext()) {
                    list.add(file.nextLine());
                }
            } else {
                throw new RuntimeException("Файл " + fileName + " пустой.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Файл " + fileName + " не найден.");
        }
    }

    public static void convertToOddNumbersList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getWithoutRepeatsList(ArrayList<Integer> list) {
        ArrayList<Integer> withoutRepeatsList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.indexOf(list.get(i)) == i) {
                withoutRepeatsList.add(list.get(i));
            }
        }

        return withoutRepeatsList;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
