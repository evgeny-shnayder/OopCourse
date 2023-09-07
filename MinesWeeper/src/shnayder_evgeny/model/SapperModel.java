package shnayder_evgeny.model;

public class SapperModel {
    private int clickCoordinateX;
    private int clickCoordinateY;
    private int xSize, ySize;
    private SapperField field;

    public SapperModel() {
    }

    public void createField (int xSize, int ySize) {
        field = new SapperField(xSize, ySize);
    }

    public int getMinCount(int x, int y) {
        return field.getMinCount(x, y);
    }

    public FieldStatus getFieldStatus(int x, int y) {
        return field.getFieldStatus(x, y);
    }


}
