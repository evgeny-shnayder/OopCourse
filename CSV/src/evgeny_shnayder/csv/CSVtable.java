package evgeny_shnayder.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVtable {
    private ArrayList<ArrayList<String>> table;

    public ArrayList<ArrayList<String>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<String>> table) {
        this.table = table;
    }

    public CSVtable() {
        table = new ArrayList<>();
    }

    public void fileScanner(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                table.add(i, new ArrayList<>());

                if (!line.contains("\"")) {
                    Scanner scanner = new Scanner(line);
                    scanner.useDelimiter(",");

                    while (scanner.hasNext()) {
                        table.get(i).add(scanner.next());
                    }

                    if (line.charAt(line.length() - 1) == ',') {
                        table.get(i).add("");
                    }
                } else {
                    String tableElement;

                    for (int j = 0; j < line.length(); j++) {
                        int k = j;

                        if (line.charAt(j) != '"') {
                            while (k < line.length()) {
                                if (line.charAt(k) == ',') {
                                    break;
                                }

                                k++;
                            }

                            tableElement = line.substring(j, k);
                            table.get(i).add(tableElement);

                            j = k;
                            continue;
                        }

                        int quotesCount = 0;

                        while (k < line.length() - 1) {
                            if (line.charAt(k) == '\"') {
                                quotesCount++;
                            }

                            if (line.charAt(k) == '"' && line.charAt(k + 1) == ',' && quotesCount % 2 == 0) {
                                break;
                            }

                            k++;
                        }

                        if (k == line.length()) {
                            tableElement = line.substring(j, k);
                        } else {
                            tableElement = line.substring(j, k + 1);
                        }

                        table.get(i).add(tableElement);

                        k++;
                        j = k;
                    }
                }

                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processingCSV() {
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                String detail;

                if (table.get(i).get(j).contains("&")) {
                    detail = table.get(i).get(j).replace("&", "&amp");
                    table.get(i).set(j, detail);
                }

                if (table.get(i).get(j).contains("<")) {
                    detail = table.get(i).get(j).replace("<", "&lt");
                    table.get(i).set(j, detail);
                }

                if (table.get(i).get(j).contains(">")) {
                    detail = table.get(i).get(j).replace(">", "&qt");
                    table.get(i).set(j, detail);
                }

                if (table.get(i).get(j).indexOf("\"") == 0
                        && table.get(i).get(j).lastIndexOf("\"") == table.get(i).get(j).length() - 1) {
                    detail = table.get(i).get(j).substring(1, table.get(i).get(j).length() - 1);
                    table.get(i).set(j, detail);
                }

                if (j == table.get(i).size() - 1 && table.get(i).get(j).contains("\"")) {
                    detail = table.get(i).get(j).concat("<br>").concat(table.get(i + 1).get(0));
                    String str2 = detail.substring(1, detail.length() - 1);
                    table.get(i).set(j, str2);
                    table.get(i + 1).remove(0);
                    table.get(i).addAll(table.get(i + 1));
                    table.remove(i + 1);
                }

                if (table.get(i).get(j).contains("\"")) {
                    detail = table.get(i).get(j).replaceFirst("\"", "");
                    table.get(i).set(j, detail);
                }

                if (table.get(i).get(j).contains("\"")) {
                    detail = table.get(i).get(j).replace("\"", "&#34");
                    table.get(i).set(j, detail);
                }
            }
        }
    }

    public void convertToHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>").append("\n");

        for (ArrayList<String> strings : table) {
            builder.append("  <tr>").append("\n");

            for (String string : strings) {
                builder.append("      <td>").append(string).append("</td>").append("\n");
            }

            builder.append("  </tr>").append("\n");
        }

        builder.append("</table>");

        try (PrintWriter writer = new PrintWriter("aut.html")) {
            writer.println(builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
