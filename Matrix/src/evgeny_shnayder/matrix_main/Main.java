package evgeny_shnayder.matrix_main;

import evgeny_shnayder.matrix.Matrix;
import shnayder_evgeny.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 4);

        for (Vector e : matrix.getMatrix()) {
            System.out.print(Arrays.toString(e.getVector()));
            System.out.println();
        }

        System.out.println();
//        System.out.println(Arrays.toString(matrix.getVector()));

        Matrix matrix1 = new Matrix(matrix);

        for (Vector e : matrix1.getMatrix()) {
            System.out.print(Arrays.toString(e.getVector()));
            System.out.println();
        }

        double [][] array = {{1, 2 , 3}, {2, 4, 5}};

        Matrix matrix2 = new Matrix(array);

        for (Vector e : matrix2.getMatrix()) {
            System.out.print(Arrays.toString(e.getVector()));
            System.out.println();
        }

        Vector [] vectors = {new Vector(3), new Vector(3), new Vector(2)};

        Matrix matrix3 = new Matrix(vectors);

        for (Vector e : matrix3.getMatrix()) {
            System.out.print(Arrays.toString(e.getVector()));
            System.out.println();
        }
//        System.out.println("length: " + array[0].length);
    }
}