package shnayder_evgeny.vector_main;

import shnayder_evgeny.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);

        System.out.println("Вектор 1: " + vector1 + " имеет размерность: " + vector1.getSize());

        double[] array = {1.0, 2.0, 3.1, 5.2, 7};

        Vector vector3 = new Vector(array);
        Vector vector2 = new Vector(3, array);

        System.out.println("Вектор номер 3: " + vector3);

        vector2.setComponent(2, 5.2);

        System.out.println("Вектор номер 2: " + vector2);

        vector3.add(vector2);
        System.out.println("Сумма двух векторов: " + vector3);
        System.out.println("Вектор номер 2: " + vector2);

        vector3.subtract(vector2);
        System.out.println("Разность двух векторов: " + vector3);
        System.out.println("Вектор номер 2: " + vector2);

        vector2.multiplyByScalar(2);
        System.out.println("Умножение вектора на число: " + vector2);
        System.out.println("Длина вектора: " + vector2.getLength());

        vector2.reverse();
        System.out.println("Разворот вектора: " + vector2);

        Vector vector4 = new Vector(vector2);
        System.out.println(vector2);

        vector4.setComponent(2, 9.2);
        System.out.println(vector4.getComponent(6));

        System.out.println("Сложение двух векторов: " + Vector.getSum(vector4, vector3));
        System.out.println("Вычитание двух векторов: " + Vector.getDifference(vector4, vector3));
        System.out.println("Скалярное произведение двух векторов: " + Vector.getScalarProduct(vector2, vector3));
        System.out.println("Hash " + vector4 + " = " + vector4.hashCode());
        System.out.println("Hash " + vector2 + " = " + vector2.hashCode());
        System.out.println(vector4.equals(vector2) ? "векторы равны" : "векторы не равны");
    }
}
