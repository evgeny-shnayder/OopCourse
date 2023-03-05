package evgeny_shnayder.matrix;

import shnayder_evgeny.vector.Vector;

import java.util.Arrays;

public class Matrix extends Vector {
    private Vector[] row;

    private int rowSize;

    private int columSize;

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

    public Matrix(double[][] array) {
        super(array[0].length);
        rowSize = array.length;
        columSize = array[0].length;
        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(Arrays.copyOf(array[i], columSize));
        }
    }

    public Matrix(Vector[] vector) {
        super(vector[0].getSize());

        rowSize = vector.length;
        columSize = vector[0].getVector().length;

        for (Vector e : vector) {
            if (columSize < e.getSize()) {
                columSize = e.getSize();
            }
        }

        row = new Vector[rowSize];

        for (int i = 0; i < rowSize; i++) {
            row[i] = new Vector(Arrays.copyOf(vector[i].getVector(), columSize));
        }
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        if (rowSize <= 0) {
            throw new IllegalArgumentException("Размер строк имеет не корректное значение.");
        }

        int minSize = Math.min(this.rowSize, rowSize);

        Vector[] vectors = new Vector[rowSize];

        for (int i = 0; i < minSize; i++) {
            vectors[i] = new Vector(Arrays.copyOf(row[i].getVector(), columSize));
        }

        this.rowSize = rowSize;
        this.row = vectors;
    }

    public int[] getMatrixSize() {
        return new int[]{rowSize, columSize};
    }

    public int getColumSize() {
        return columSize;
    }

    public void setColumSize(int columSize) {
        if (columSize <= 0) {
            throw new IllegalArgumentException("Длина столбцов имеет не корректное значение.");
        }

        this.columSize = columSize;

        for (int i = 0; i < rowSize; i++) {
            this.row[i] = new Vector(Arrays.copyOf(row[i].getVector(), this.columSize));
        }
    }

    public void setRow(Vector[] row) {
        rowSize = row.length;
        int maxColumSize = row[0].getSize();

        for (Vector e : row) {
            if (maxColumSize < e.getSize()) {
                maxColumSize = e.getSize();
            }
        }

        columSize = maxColumSize;

        Vector[] vectors = new Vector[rowSize];

        for (int i = 0; i < this.rowSize; i++) {
            vectors[i] = new Vector(Arrays.copyOf(row[i].getVector(), columSize));
        }

        this.row = vectors;
    }

    public Vector getIndexRow(int index) {
        if (index >= rowSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс больше размерности матрицы или имеет недопустимое значение.");
        }

        return row[index];
    }

    public void setIndexRow(int index, Vector value) {
        if (index >= rowSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс больше размерности матрицы или имеет недопустимое значение.");
        }

        row[index] = value;
    }

    public Vector getColum(int index) {
        if (index >= columSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс больше размерности матрицы или имеет недопустимое значение.");
        }

        double[] array = new double[rowSize];

        for (int i = 0; i < rowSize; i++) {
            array[i] = row[i].getIndexValue(index);
        }

        return new Vector(array);
    }

    public Matrix transpose() {
        Matrix transposeMatrix = new Matrix(columSize, rowSize);

        for (int i = 0; i < columSize; i++) {
            double[] array = new double[rowSize];

            for (int k = 0; k < rowSize; k++) {
                array[k] = row[k].getIndexValue(i);
            }

            transposeMatrix.setIndexRow(i, new Vector(array));
        }

        return transposeMatrix;
    }

    public Matrix getMultiplication(double scalar) {
        Matrix needMatrix = new Matrix(rowSize, columSize);

        for (int i = 0; i < rowSize; i++) {
            needMatrix.setIndexRow(i, row[i].getMultiplication(scalar));
        }

        return needMatrix;
    }

    /*Умножение матрицы на вектор-строку*/
    public Matrix getVectorMultiplication(Vector vector) {
        if (columSize != 1 || rowSize != vector.getSize()) {
            throw new IllegalArgumentException("Не верные входные значения. " +
                    "Для умножения на вектор-строку матрица должна быть матрицей-столбцом./ " +
                    "Или количество строк не совпадает с длиной вектора.");
        }

        Matrix needMatrix = new Matrix(rowSize, vector.getSize());

        for (int i = 0; i < rowSize; i++) {
            for (int k = 0; k < rowSize; k++) {
                needMatrix.row[i].setIndexValue(k, row[i].getIndexValue(0) * vector.getIndexValue(k));
            }
        }

        return needMatrix;
    }

    public Matrix getSum(Matrix matrix) {
        if (rowSize != matrix.rowSize || columSize != matrix.columSize) {
            throw new IllegalArgumentException("Размерность матриц не совпадает.");
        }

        Matrix needMatrix = new Matrix(rowSize, columSize);

        for (int i = 0; i < rowSize; i++) {
            needMatrix.row[i] = getSum(row[i], matrix.row[i]);
        }

        return needMatrix;
    }

    public Matrix getDifference(Matrix matrix) {
        if (rowSize != matrix.rowSize || columSize != matrix.columSize) {
            throw new IllegalArgumentException("Размерность матриц не совпадает.");
        }

        Matrix needMatrix = new Matrix(rowSize, columSize);

        for (int i = 0; i < rowSize; i++) {
            needMatrix.row[i] = getDifference(row[i], matrix.row[i]);
        }

        return needMatrix;
    }

    public double getDeterminant() {
        if (rowSize != columSize) {
            throw new IllegalArgumentException("Матрица не квадратная, расчет определителя не возможен.");
        }

        Matrix copyMatrix = new Matrix(this);

        double determinant = 1;
        double zeroCoefficient;

        for (int i = 0; i < copyMatrix.rowSize; i++) {
            if (copyMatrix.row[i].getVector()[i] == 0) {
                for (int j = i + 1; j < copyMatrix.rowSize; j++) {

                    for (int k = 0; k < copyMatrix.rowSize; k++) {
                        copyMatrix.row[i].getVector()[k] += copyMatrix.row[j].getVector()[k];
                    }
                }
            }
            if (copyMatrix.row[i].getVector()[i] != 0) {
                for (int j = i + 1; j < copyMatrix.rowSize; j++) {
                    zeroCoefficient = copyMatrix.row[j].getVector()[i] / copyMatrix.row[i].getVector()[i];

                    for (int k = 0; k < copyMatrix.rowSize; k++) {
                        copyMatrix.row[j].getVector()[k] -= copyMatrix.row[i].getVector()[k] * zeroCoefficient;
                    }
                }
            }
        }

        for (int i = 0; i < copyMatrix.rowSize; i++) {
            determinant *= copyMatrix.row[i].getVector()[i];
        }


        return determinant;
    }

    @Override
    public String toString() {
        return Arrays.toString(row).replace("[", "{").replace("]", "}");
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rowSize != matrix2.rowSize || matrix1.columSize != matrix2.columSize) {
            throw new IllegalArgumentException("Размерность матриц не совпадает.");
        }

        Matrix needMatrix = new Matrix(matrix1.rowSize, matrix1.columSize);

        for (int i = 0; i < matrix1.rowSize; i++) {
            needMatrix.row[i] = getSum(matrix1.row[i], matrix2.row[i]);
        }

        return needMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rowSize != matrix2.rowSize || matrix1.columSize != matrix2.columSize) {
            throw new IllegalArgumentException("Размерность матриц не совпадает.");
        }

        Matrix needMatrix = new Matrix(matrix1.rowSize, matrix1.columSize);

        for (int i = 0; i < matrix1.rowSize; i++) {
            needMatrix.row[i] = getDifference(matrix1.row[i], matrix2.row[i]);
        }

        return needMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.columSize != matrix2.rowSize) {
            throw new IllegalArgumentException("Число столбцов в первой матрице не совпадает с количеством строк во второй.");
        }

        double[][] array = new double[matrix1.rowSize][matrix2.columSize];

        for (int i = 0; i < matrix1.rowSize; i++) {
            for (int j = 0; j < matrix2.columSize; j++) {
                for (int k = 0; k < matrix1.columSize; k++) {
                    array[i][j] += (matrix1.row[i].getVector()[k] * matrix2.row[k].getVector()[j]);
                }
            }
        }

        return new Matrix(array);
    }
}
