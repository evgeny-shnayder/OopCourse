package evgeny_shnayder.matrix_main;

import evgeny_shnayder.matrix.Matrix;
import shnayder_evgeny.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);

        System.out.println("Матрица 1: " + matrix1);

        double[][] array = {{1, 2, 3}, {2, 4, 5}};

        Matrix matrix2 = new Matrix(array);

        System.out.println("Матрица 2: " + matrix2);

        Vector[] vectors = {new Vector(2, new double[]{1.2, 5}),
                new Vector(3, new double[]{1.0, 2.3, 3.4}),
                new Vector(1, new double[]{8.1})};

        Matrix matrix3 = new Matrix(vectors);

        System.out.println("Матрица 3: " + matrix3);

        System.out.println("Размер матрицы: " + Arrays.toString(matrix1.getMatrixSize()));

        matrix1.setRow(vectors);

        System.out.println("Изменение матрицы путем замены строк: " + matrix1);

        matrix1.setIndexRow(1, new Vector(new double[]{1.0, 1.0, 1.0}));

        System.out.println("Изменение вектора строки матрицы :" + matrix1);

        Matrix matrix4 = matrix2.transpose();

        System.out.println("Транспонирование матрицы: " + matrix4);

        Matrix matrix5 = matrix4.getMultiplication(2);

        System.out.println("Умножение на скаляр: " + matrix5);

        matrix5.setColumSize(1);

        matrix2.setRowSize(1);

        System.out.println("Матрица 2: " + matrix2);

        System.out.println("Размер матрицы: " + Arrays.toString(matrix2.getMatrixSize()));

        System.out.println("Столбец матрицы: " + matrix3.getColum(1));

        System.out.println("Строка матрицы: " + matrix3.getIndexRow(1));

        System.out.println("Произведение на вектор: " + matrix5.getVectorMultiplication(new Vector(new double[]{1, 3, 5})));

        System.out.println("Сумма матриц: " + matrix3.getSum(matrix1));

        System.out.println("Разность матриц: " + matrix3.getDifference(matrix1));

        System.out.println("Произведение матриц, статический метод: " + Matrix.getMultiplication(matrix2, matrix4));

        System.out.println("Сумма матриц, статический метод:" + Matrix.getSum(matrix3, matrix1));

        System.out.println("Разность матриц, статический метод:" + Matrix.getDifference(matrix3, matrix1));

        System.out.println("Определитель матрицы: " + matrix1.getDeterminant());
    }
}
