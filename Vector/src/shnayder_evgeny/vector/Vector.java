package shnayder_evgeny.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorComponents;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора " + size + " не может быть отрицательным или равен нулю.");
        }

        vectorComponents = new double[size];
    }

    public Vector(Vector vector) {
        this.vectorComponents = Arrays.copyOf(vector.vectorComponents, vector.vectorComponents.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива не может быть отрицательной.");
        }

        this.vectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size < 0) {
            throw new IllegalArgumentException("Длина массива " + size + " не может быть отрицательной.");
        }

        vectorComponents = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return vectorComponents.length;
    }

    public void add(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        vector.vectorComponents = Arrays.copyOf(vector.vectorComponents, vectorComponents.length);

        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] += vector.vectorComponents[i];
        }
    }

    public void subtract(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        vector.vectorComponents = Arrays.copyOf(vector.vectorComponents, vectorComponents.length);

        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] -= vector.vectorComponents[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] *= scalar;
        }
    }

    public void reversal() {
        for (int i = 0; i < vectorComponents.length; i++) {
            if (vectorComponents[i] != 0) {
                vectorComponents[i] *= -1;
            }
        }
    }

    public double getLength() {
        double squaresSum = 0;

        for (double e : vectorComponents) {
            squaresSum += e * e;
        }

        return Math.abs(Math.sqrt(squaresSum));
    }

    public double getComponent(int index) {
        if (index >= vectorComponents.length || index < 0) {
            throw new IllegalArgumentException("Заданный индекс " + index + " выходит за размер вектора.");
        }

        return vectorComponents[index];
    }

    public void setComponent(int index, double value) {
        if (index >= vectorComponents.length || index < 0) {
            throw new IllegalArgumentException("Заданный индекс " + index + " выходит за размер вектора.");
        }

        vectorComponents[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder vectorString = new StringBuilder();

        vectorString.append("{");

        for (int i = 0; i < vectorComponents.length; i++) {
             vectorString.append(vectorComponents[i]);

             if (i < vectorComponents.length - 1) {
                 vectorString.append(", ");
             }
        }

        vectorString.append("}");
        return vectorString.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        if (vector.vectorComponents.length != vectorComponents.length) {
            return false;
        }

        return Arrays.equals(vector.vectorComponents, vectorComponents);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorComponents);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector neededVector = new Vector(Arrays.copyOf(vector1.vectorComponents, vector1.vectorComponents.length));

        neededVector.add(vector2);

        return neededVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector neededVector = new Vector(Arrays.copyOf(vector1.vectorComponents, vector1.vectorComponents.length));

        neededVector.subtract(vector2);

        return neededVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.vectorComponents.length, vector2.vectorComponents.length);
        double scalar = 0;

        for (int i = 0; i < minSize; i++) {
            scalar += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return scalar;
    }
}
