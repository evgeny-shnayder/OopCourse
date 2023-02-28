package shnayder_evgeny.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    private int size;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю.");
        }

        this.size = size;
        vector = new double[size];
    }

    public Vector(Vector vector) {
        if (vector.size <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю.");
        }

        this.size = vector.vector.length;
        this.vector = Arrays.copyOf(vector.vector, vector.size);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю.");
        }

        this.size = array.length;
        this.vector = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю.");
        }

        this.size = size;
        vector = Arrays.copyOf(array, size);
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
        this.size = vector.length;
    }

    public int getSize() {
        return size;
    }

    private static double[] getSumOrDifference(double[] array1, double[] array2, int sign1, int sign2) {
        double[] needArray = Arrays.copyOf(array1, array2.length);

        for (int i = 0; i < needArray.length; i++) {
            needArray[i] = needArray[i] * sign1 + array2[i] * sign2;
        }

        return needArray;
    }

    public Vector getSum(Vector vector) {
        if (size < vector.size) {
            return new Vector(getSumOrDifference(this.vector, vector.vector, 1, 1));
        }

        return new Vector(getSumOrDifference(vector.vector, this.vector, 1, 1));
    }

    public Vector getDifference(Vector vector) {
        if (size < vector.size) {
            return new Vector(getSumOrDifference(this.vector, vector.vector, 1, -1));
        }

        return new Vector(getSumOrDifference(vector.vector, this.vector, -1, 1));
    }

    public Vector getMultiplication(int scalar) {
        double[] needVector = Arrays.copyOf(this.vector, size);

        for (int i = 0; i < size; i++) {
            needVector[i] *= scalar;
        }

        return new Vector(needVector);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double e : vector) {
            squaresSum += e;
        }

        return Math.sqrt(squaresSum);
    }

    public double getIndexValue(int index) {
        return vector[index];
    }

    public void setIndexValue(int index, double value) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс больше размерности вектора.");
        }

        Arrays.fill(vector, index, index + 1, value);
    }

    @Override
    public String toString() {
        return Arrays.toString(vector).replace("[", "{").replace("]", "}");
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

        for (int i = 0; i < vector.size; i++) {
            if (vector.vector[i] != this.vector[i]) {
                return false;
            }
        }

        return vector.size == size;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (double e : vector) {
            hash *= prime + Double.hashCode(e);
        }

        return hash * prime + Double.hashCode(size);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1.size < vector2.size) {
            return new Vector(getSumOrDifference(vector1.vector, vector2.vector, 1, 1));
        }

        return new Vector(getSumOrDifference(vector2.vector, vector1.vector, 1, 1));
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1.size < vector2.size) {
            return new Vector(getSumOrDifference(vector1.vector, vector2.vector, 1, -1));
        }

        return new Vector(getSumOrDifference(vector2.vector, vector1.vector, -1, 1));
    }

    public static Vector getMultiplication(Vector vector1, Vector vector2) {
        int minLength = Math.min(vector1.size, vector2.size);
        double[] needVector = new double[minLength];

        for (int i = 0; i < minLength; i++) {
            needVector[i] = vector1.vector[i] * vector2.vector[i];
        }

        needVector = Arrays.copyOf(needVector, Math.max(vector1.size, vector2.size));

        return new Vector(needVector);
    }
}
