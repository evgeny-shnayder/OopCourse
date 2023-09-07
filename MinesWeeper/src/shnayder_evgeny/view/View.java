package shnayder_evgeny.view;

import shnayder_evgeny.controller.SapperController;

public interface View {
    void start();

    void updateField();

    void setController(SapperController sapperController);

    void showMessage(String message);
}
