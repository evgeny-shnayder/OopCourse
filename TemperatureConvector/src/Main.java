import evgeniy_shnayder.controller.Controller;
import evgeniy_shnayder.model.TemperatureModel;
import evgeniy_shnayder.view.SwingView;
import evgeniy_shnayder.view.View;


public class Main {
    public static void main(String[] args) {
        TemperatureModel model = new TemperatureModel();
        Controller controller = new Controller(model);
        View view = new SwingView(controller);

        controller.setView(view);
        view.start();
    }
}
