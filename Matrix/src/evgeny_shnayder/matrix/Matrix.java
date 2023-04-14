package evgeny_shnayder.matrix;

import shnayder_evgeny.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        checkRowsCount(rowsCount);
        checkColumnsCount(columnsCount);

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        checkRowsCount(array.length);

        int columnsCount = array[0].length;

        for (double[] row : array) {
            if (columnsCount < row.length) {
                columnsCount = row.length;
            }
        }

        checkColumnsCount(columnsCount);

        rows = new Vector[array.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        checkRowsCount(vectors.length);

        int columnsCount = vectors[0].getSize();

        for (Vector row : vectors) {
            if (columnsCount < row.getSize()) {
                columnsCount = row.getSize();
            }
        }

        checkColumnsCount(columnsCount);

        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
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
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен иметь значение от min 0 до max " + rows.length + ".");
        }
    }

    private void checkEqualitySize(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размер матрицы " + rows.length + "x" + getColumnsCount() + " не совпадает с " +
                    "размером матрицы " + matrix.rows.length + "x" + matrix.getColumnsCount() + ".");
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        checkIndex(index);

        if (getColumnsCount() != row.getSize()) {
            throw new IllegalArgumentException("Размер вектора " + row.getSize() + " не соответствует количеству столбцов "
                    + getColumnsCount() + " матрицы.");
        }

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[index].setComponent(i, row.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен иметь значение от min 0 до max " + getRowsCount() + ".");
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            array[i] = rows[i].getComponent(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] columns = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов " + getColumnsCount() + " в матрице должно быть равным " +
                    " размеру " + vector.getSize() + " вектора - столбца.");
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkEqualitySize(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkEqualitySize(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Матрица размера " + rows.length + "x" + getColumnsCount() + " не квадратная," +
                    " расчет определителя не возможен.");
        }

        Vector[] vectors = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            vectors[i] = new Vector(rows[i]);
        }

        double determinant = 1;
        double epsilon = 1e-10;

        for (int i = 0; i < vectors.length; i++) {
            if (Math.abs(vectors[i].getComponent(i)) <= epsilon) {
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
        matrix1.checkEqualitySize(matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkEqualitySize(matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов " + matrix1.getColumnsCount() + " в первой матрице не совпадает" +
                    " с количеством строк " + matrix2.rows.length + " во второй.");
        }

        double[][] array = new double[matrix1.rows.length][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                array[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}
