package evgeniy_shnayder.controller;

import evgeniy_shnayder.model.TemperatureModel;
import evgeniy_shnayder.view.View;

public class Controller {
    private final TemperatureModel temperatureModel;
    private View view;

    public Controller(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    public void setView(evgeniy_shnayder.view.View view) {
        this.view = view;
    }

    public void convertTemperature(double celsiusTemperature, int index1, int index2) {
        try {
            double temperature = temperatureModel.convertTemperature(celsiusTemperature, index1, index2);
            view.updateTemperature(temperature);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
}
