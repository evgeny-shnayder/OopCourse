package evgeny_shnayder.csv_main;

import evgeny_shnayder.csv.CSVtable;

public class Main {
    public static void main(String[] args) {
        String fileName = "fille.txt";

        CSVtable table = new CSVtable();
        table.fileScanner(fileName);
        table.processingCSV();
        table.convertToHTML();
    }
}
