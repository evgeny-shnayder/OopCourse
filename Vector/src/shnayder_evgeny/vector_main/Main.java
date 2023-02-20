package shnayder_evgeny.vector_main;

import shnayder_evgeny.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(4);

        vector.setVector(new double[]{1});

        double[] array = new double[]{1.0, 2.0, 3.1, 5.2, 7};

        Vector vector2 = new Vector(array);

        Vector vector1 = new Vector(6, array);

        System.out.println(vector);

        System.out.println(vector2);

        System.out.println(vector1);

        System.out.println("Сумма двух векторов: " + vector2.getSum(vector1));

        System.out.println(vector2);

        System.out.println(vector1);

        System.out.println("Разность двух векторов: " + vector2.getDifference(vector1));

        System.out.println("Умножение вектора на число: " + vector1.getMultiplication(2));

        System.out.println("Длина вектора: " + vector1.getLength());

        System.out.println("Разворот вектора: " + vector1.getMultiplication(-1));
    }
}