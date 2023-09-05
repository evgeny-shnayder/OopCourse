package evgeniy_shnayder.model;

public class TemperatureModel {
    private double convertTemperature(double temperature, int index1) {
        double celsiusTemperature = temperature;
        switch (index1) {
            case 0 -> celsiusTemperature = temperature - 273.15;
            case 1 -> celsiusTemperature = (temperature - 32) / 1.8;
        }

        return celsiusTemperature;
    }

    private void checkTemperature(double temperature, int index1) {
        if (index1 == 0 && temperature < 0) {
            throw new IllegalArgumentException("Значение по шкале Кельвина не должно быть менее 0, текущее значение: " + temperature);
        }

        if (index1 == 1 && temperature < -459.67) {
            throw new IllegalArgumentException("Значение по шкале Фаренгейта не должно быть менее -459,67," +
                    " текущее значение: " + temperature);
        }

        if (index1 == 2 && temperature < -273.15) {
            throw new IllegalArgumentException("Значение по шкале Цельсия не должно быть менее -273,15," +
                    " текущее значение: " + temperature);
        }
    }

    public double convertTemperature(double temperature, int index1, int index2) {
        checkTemperature(temperature, index1);

        if (index1 == index2) {
            return temperature;
        }

        double celsiusTemperature = convertTemperature(temperature, index1);
        double needTemperature;

        switch (index2) {
            case 0 -> needTemperature = celsiusTemperature + 273.15;
            case 1 -> needTemperature = celsiusTemperature * 9 / 5.0 + 32;
            default -> needTemperature = celsiusTemperature;
        }

        return needTemperature;
    }
}
