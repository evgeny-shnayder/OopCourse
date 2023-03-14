package evgeny_shnayder.array_list;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome<T> {
    private List<T> list;

    public ArrayListHome(String fileName) {
        try (Scanner file = new Scanner(new FileInputStream(fileName))) {
            if (file.hasNext()) {
                list = new ArrayList<>();

                while (file.hasNext()) {
                    int i = 0;

                    T[] line = (T[]) file.nextLine().split(" ");

                    if (line.length < 2) {
                        list.add(line[i]);
                    } else {
                        while (i < line.length) {
                            list.add(line[i]);
                            i++;
                        }
                    }
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
            if ( list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public ArrayList<T> getWithoutRepeatsList() {
        ArrayList<T> withoutRepeatsList = new ArrayList<>();

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
