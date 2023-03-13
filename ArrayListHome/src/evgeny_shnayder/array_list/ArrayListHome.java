package evgeny_shnayder.array_list;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    private ArrayList<Integer> list;

    public ArrayListHome(String fileName) {
        try {
            Scanner file = new Scanner(new FileInputStream(fileName));

            if (file.hasNext()) {
                list = new ArrayList<>();

                while (file.hasNext()) {
                    list.add(file.nextInt());
                }
            } else {
                throw new RuntimeException("Файл " + fileName + " пустой.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл " + fileName + " не найден.");
        }
    }

    public void convertToOddNumbersList() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public ArrayList<Integer> getWithoutRepeatsList() {
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
