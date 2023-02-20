package shnayder_evgeny.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    final int size;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю.");
        }

        this.size = size;
        vector = new double[size];
    }

    public Vector(Vector vector) {
        this.size = vector.vector.length;
        this.vector = vector.vector;
    }

    public Vector(double[] array) {
        this.size = array.length;
        this.vector = array;
    }

    public Vector(int size, double[] array) {
        this.size = size;
        vector = Arrays.copyOf(array, size);
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public int getSize() {
        return size;
    }

    private double[] getSumOrDifference(double[] array1, double [] array2, int sign) {
        double[] resultArray = array1;

        for (int i = 0; i < array1.length; i++) {
            resultArray[i] += array2[i] * sign;
        }

        return resultArray;
    }
    public Vector getSum(Vector vector) {
        double[] needVector;

        if (size < vector.size) {
            needVector = Arrays.copyOf(this.vector, vector.size);

            return new Vector(getSumOrDifference(needVector, vector.vector, 1));
        }

        needVector = Arrays.copyOf(vector.vector, size);

        return new Vector(getSumOrDifference(needVector, this.vector, 1));
    }

    public Vector getDifference(Vector vector) {
        double[] needVector;

        if (size < vector.size) {
            needVector = Arrays.copyOf(this.vector, vector.size);

            return new Vector(getSumOrDifference(needVector, vector.vector, -1));
        }

        needVector = Arrays.copyOf(vector.vector, size);

        return new Vector(getSumOrDifference(needVector, this.vector, -1));
    }

    public Vector getMultiplication (int scalar) {
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

    @Override
    public String toString() {
        return Arrays.toString(vector).replace("[", "{").replace("]", "}");
    }
}
