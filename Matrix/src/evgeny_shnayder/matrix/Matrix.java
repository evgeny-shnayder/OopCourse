package evgeny_shnayder.matrix;

import shnayder_evgeny.vector.Vector;

public class Matrix {
    private Vector[] rows;
    private int rowsCount;
    private int columnsCount;

    public Matrix(int rowsCount, int columnsCount) {
        checkRowsCount(rowsCount);
        checkColumnsCount(columnsCount);

        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        rows = new Vector[this.rowsCount];

        for (int i = 0; i < this.rowsCount; i++) {
            rows[i] = new Vector(this.columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        checkRowsCount(matrix.rowsCount);
        checkColumnsCount(matrix.columnsCount);

        rowsCount = matrix.rowsCount;
        columnsCount = matrix.columnsCount;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < matrix.rows[i].getSize(); j++) {
                rows[i] = new Vector(matrix.rows[i]);
            }
        }
    }

    public Matrix(double[][] array) {
        checkRowsCount(array.length);

        columnsCount = array[0].length;

        for (double[] row : array) {
            if (columnsCount < row.length) {
                columnsCount = row.length;
            }
        }

        rowsCount = array.length;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        checkRowsCount(vector.length);

        rowsCount = vector.length;
        columnsCount = vector[0].getSize();

        for (Vector row : vector) {
            if (columnsCount < row.getSize()) {
                columnsCount = row.getSize();
            }
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);

            for (int j = 0; j < vector[i].getSize(); j++) {
                rows[i].setComponent(j, vector[i].getComponent(j));
            }
        }
    }

    public int getRowsCount() {
        return rowsCount;
    }

    private void checkRowsCount(int rowsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк " + rowsCount + " должно быть больше нуля.");
        }
    }

    private void checkColumnsCount(int columnsCount) {
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов " + columnsCount + " должно быть больше нуля.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в интервале от 0 до " + rowsCount + ".");
        }
    }

    private void checkSize(Matrix matrix) {
        if (rowsCount != matrix.rowsCount || columnsCount != matrix.columnsCount) {
            throw new IllegalArgumentException("Размер матрицы " + rowsCount + "x" + columnsCount + " не совпадает с " +
                    "размером матрицы " + matrix.rowsCount + "x" + matrix.columnsCount + ".");
        }
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        checkIndex(index);

        for (int i = 0; i < Math.min(row.getSize(), columnsCount); i++) {
            rows[index].setComponent(i, row.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в интервале от 0 до " + columnsCount + ".");
        }

        double[] array = new double[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            array[i] = rows[i].getComponent(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] vectors = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            vectors[i] = this.getColumn(i);
        }

        rows = vectors;
        int row = rowsCount;
        rowsCount = columnsCount;
        columnsCount = row;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < rowsCount; i++) {
            rows[i].multiplyByScalar(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (rowsCount != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов " + columnsCount + " в матрице должно быть равным" +
                    " количеству строк " + vector.getSize() + " векторе - столбце.");
        }

        Vector resultVector = new Vector(vector.getSize());

        for (int i = 0; i < rowsCount; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkSize(matrix);

        for (int i = 0; i < rowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSize(matrix);

        for (int i = 0; i < rowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public double getDeterminant() {
        if (rowsCount != columnsCount) {
            throw new UnsupportedOperationException("Матрица размера " + rowsCount + "x" + columnsCount + " не квадратная," +
                    " расчет определителя не возможен.");
        }

        Vector[] vectors = new Vector[columnsCount];

        System.arraycopy(rows, 0, vectors, 0, rowsCount);

        double determinant = 1;
        double epsilon = 1e-10;

        for (int i = 0; i < vectors.length; i++) {
            if (Math.abs(vectors[i].getComponent(i) - 0) <= epsilon) {
                for (int j = i + 1; j < vectors.length; j++) {
                    for (int k = 0; k < vectors.length; k++) {
                        vectors[i].setComponent(k, vectors[i].getComponent(k) + vectors[j].getComponent(k));
                    }
                }
            }

            if (Math.abs(vectors[i].getComponent(i) - 0) > epsilon) {
                for (int j = i + 1; j < vectors.length; j++) {
                    double zeroCoefficient = vectors[j].getComponent(i) / vectors[i].getComponent(i);

                    for (int k = 0; k < vectors.length; k++) {
                        vectors[j].setComponent(k, vectors[j].getComponent(k)
                                - vectors[i].getComponent(k) * zeroCoefficient);
                    }
                }
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            determinant *= vectors[i].getComponent(i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSize(matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSize(matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.columnsCount != matrix2.rowsCount) {
            throw new IllegalArgumentException("Число столбцов " + matrix1.columnsCount + " в первой матрице не совпадает" +
                    " с количеством строк " + matrix2.rowsCount + " во второй.");
        }

        double[][] array = new double[matrix1.rowsCount][matrix2.columnsCount];

        for (int i = 0; i < matrix1.rowsCount; i++) {
            for (int j = 0; j < matrix2.columnsCount; j++) {
                array[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}
