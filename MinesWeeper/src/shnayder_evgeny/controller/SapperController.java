package shnayder_evgeny.controller;

import shnayder_evgeny.model.FieldStatus;
import shnayder_evgeny.model.SapperModel;
import shnayder_evgeny.view.View;

public class SapperController {
    private View view;
    private SapperModel model;

    public SapperModel getModel() {
        return model;
    }

    public SapperController(SapperModel model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setModel (int x, int y) {
        //TODO
    }

    public int getMinCount(int x, int y) {
        return model.getMinCount(x, y);
    }

    public FieldStatus getFieldStatus(int x, int y) {
        return model.getFieldStatus(x, y);
    }


    public void createField(int x, int y) {
        model.createField(x, y);
    }
}
