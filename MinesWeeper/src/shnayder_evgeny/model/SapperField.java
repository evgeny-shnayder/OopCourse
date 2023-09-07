package shnayder_evgeny.model;

import java.util.Arrays;

public class SapperField {
    private int[][] field;
    private FieldStatus[][] fieldStatuses;

    public SapperField(int x, int y){
        field = new int[x][y];

        fieldStatuses = new FieldStatus[x][y];

        for (FieldStatus[] fieldStatus : fieldStatuses) {
            Arrays.fill(fieldStatus, FieldStatus.CLOSED);
        }
    }

    public int getMinCount(int x, int y) {
        return field[x][y];
    }

    public FieldStatus getFieldStatus(int x, int y) {
        return fieldStatuses[x][y];
    }

    public void setField (int x, int y, int number) {
        field[x][y] = number;
    }

    public void setFieldStatuses (int x, int y, FieldStatus status) {
        fieldStatuses[x][y] = status;
    }
}
