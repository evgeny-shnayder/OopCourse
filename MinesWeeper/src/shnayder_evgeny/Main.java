package shnayder_evgeny;

import shnayder_evgeny.controller.SapperController;
import shnayder_evgeny.model.SapperModel;
import shnayder_evgeny.view.SapperView;
import shnayder_evgeny.view.View;

public class Main {
    public static void main(String[] args) {
        SapperModel model = new SapperModel();
        SapperController controller = new SapperController(model);
        View view = new SapperView(controller);

        controller.setView(view);

        view.start();


    }
}