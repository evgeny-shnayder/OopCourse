package shnayder_evgeny.vector_main;

import shnayder_evgeny.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);

        vector1.setVector(new double[]{1});

        System.out.println("Вектор: " + vector1 + " имеет размерность: " + vector1.getSize());

        double[] array = new double[]{1.0, 2.0, 3.1, 5.2, 7};

        Vector vector3 = new Vector(array);

        Vector vector2 = new Vector(6, vector3.getVector());

        System.out.println("Вектор номер 2: " + vector3);

        vector2.setIndexValue(5, 5.2);

        System.out.println("Вектор номер 1: " + vector2);

        System.out.println("Сумма двух векторов: " + vector3.getSum(vector2));

        System.out.println("Разность двух векторов: " + vector3.getDifference(vector2));

        System.out.println("Умножение вектора на число: " + vector2.getMultiplication(2));

        System.out.println("Длина вектора: " + vector2.getLength());

        System.out.println("Разворот вектора: " + vector2.getMultiplication(-1));

        Vector vector4 = new Vector(vector2);

        vector4.setIndexValue(4, 9.2);

        System.out.println(vector4.getIndexValue(4));

        System.out.println("Сложение двух векторов: " + Vector.getSum(vector4, vector3));

        System.out.println("Вычитание двух векторов: " + Vector.getDifference(vector4, vector3));

        System.out.println("Умножение двух векторов: " + Vector.getMultiplication(vector2, vector1));
    }
}