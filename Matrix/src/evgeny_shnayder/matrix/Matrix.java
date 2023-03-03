package evgeny_shnayder.matrix;

import shnayder_evgeny.vector.Vector;

import java.util.Arrays;

public class Matrix extends Vector {
    private Vector[] row;

    private int rowSize;

    private  int columSize;

    public Matrix(int n, int m) {
        super(m);
        rowSize = n;
        columSize = m;

        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(columSize);
        }
    }

    public Matrix(Matrix matrix) {
        super(matrix.columSize);

        rowSize = matrix.rowSize;
        columSize = matrix.columSize;
        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(Arrays.copyOf(matrix.row[i].getVector(), matrix.columSize));
        }
    }

    public Matrix(double [] [] array) {
        super(array[0].length);
        rowSize = array.length;
        columSize = array[0].length;

        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(Arrays.copyOf(array[i], columSize));
        }
    }

    public Matrix(Vector [] vector) {
        super(vector[0].getSize());

        rowSize = vector.length;
        columSize = vector[0].getVector().length;

        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(Arrays.copyOf(vector[i].getVector(), vector[i].getVector().length));
        }
    }
    public Vector[] getMatrix() {
        return row;
    }

}
