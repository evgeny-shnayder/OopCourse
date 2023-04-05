package evgeny_shnayder.matrix_main;

import evgeny_shnayder.matrix.Matrix;
import shnayder_evgeny.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);

        System.out.println("Матрица 1: " + matrix1);

        double[][] array = {{1, 2, 3}, {4, 5, 6}};

        Matrix matrix2 = new Matrix(array);

        System.out.println("Матрица 2: " + matrix2);

        Vector[] vectors = {
                new Vector(new double[]{1.2, 5}),
                new Vector(new double[]{1.0, 2.3, 3.4}),
                new Vector(new double[]{8.1})
        };

        Matrix matrix3 = new Matrix(vectors);

        System.out.println("Матрица 3: " + matrix3);

        Vector vector = new Vector(new double[]{1, 6, 9, 17});

        matrix1.setRow(0, vector);

        System.out.println("Изменение матрицы путем замены строки: " + matrix1);

        matrix1.setRow(1, new Vector(new double[]{1.0, 1.0, 1.0}));

        System.out.println("Изменение вектора строки матрицы : " + matrix1);

        matrix2.transpose();

        System.out.println("Транспонирование матрицы: " + matrix2);

        matrix3.multiplyByScalar(2);

        System.out.println("Умножение на скаляр: " + matrix3);

        System.out.println("Матрица 2: " + matrix2);

        System.out.println("Столбец матрицы: " + matrix3.getColumn(1));

        System.out.println("Строка матрицы: " + matrix3.getRow(1));

        System.out.println("Произведение на вектор: " + matrix3.multiplyByVector(new Vector(new double[]{1, 3, 5})));

        System.out.println("Матрица 3: " + matrix3);
        System.out.println("Матрица 1: " + matrix1);

        matrix3.add(matrix1);
        System.out.println("Сумма матриц: " + matrix3);

        matrix3.subtract(matrix1);
        System.out.println("Разность матриц: " + matrix3);

        matrix2.transpose();

        System.out.println("Матрица2: " + matrix2);
        System.out.println("Матрица3: " + matrix3);
        System.out.println("Произведение матриц, статический метод: " + Matrix.getProduct(matrix2, matrix3));

        System.out.println("Матрица3: " + matrix3);
        System.out.println("Матрица1: " + matrix1);
        System.out.println("Сумма матриц, статический метод: " + Matrix.getSum(matrix3, matrix1));

        System.out.println("Матрица3: " + matrix3);
        System.out.println("Матрица1: " + matrix1);
        System.out.println("Разность матриц, статический метод: " + Matrix.getDifference(matrix3, matrix1));

        Matrix matrix4 = new Matrix(new Vector[]{
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{4, 5, 6}),
                new Vector(new double[]{7, 8, 5})
        });

        System.out.println("Определитель матрицы: " + matrix4.getDeterminant());
    }
}
