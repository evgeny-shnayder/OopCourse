package evgeniy_shnayder.view;

import evgeniy_shnayder.controller.Controller;

public interface View {
    void start();

    void updateTemperature(double temperature);

    void setController(Controller controller);

    void showError(String message);
}
