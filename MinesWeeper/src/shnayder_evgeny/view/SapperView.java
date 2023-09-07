package shnayder_evgeny.view;

import shnayder_evgeny.controller.SapperController;
import shnayder_evgeny.model.FieldStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SapperView implements View{
    private int X_SIZE;
    private int Y_SIZE;
    private JFrame frame;
    private SapperController sapperController;

    public SapperView() {
    }

    public SapperView (SapperController sapperController) {
        this.sapperController = sapperController;
    }

    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        frame = new JFrame("MinesWeeper");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("Выберите размер поля:");

        JComboBox<Integer> comboBox1 = new JComboBox<>(new Integer[]{20, 30, 40, 50});
        JComboBox<Integer> comboBox2 = new JComboBox<>(new Integer[]{20, 30, 40, 50});

        frame.setContentPane(panel);

        JButton button = new JButton("go!");

        panel.add(label1);
        panel.add(comboBox1);
        panel.add(comboBox2);
        panel.add(button);

        JPanel panel1 = new JPanel();

        button.addActionListener(e -> {
            try {
                X_SIZE = (int) comboBox1.getSelectedItem();
                Y_SIZE = (int) comboBox2.getSelectedItem();

                panel1.setSize(new Dimension(X_SIZE, Y_SIZE));

                panel.repaint();
                sapperController.createField(X_SIZE, Y_SIZE);
//                panel.add(sapperController.getModel());
            } catch (NullPointerException ex) {
                throw new NullPointerException(ex.getMessage());
            }
        });




        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

            }
        });

        frame.setVisible(true);
    }

    @Override
    public void updateField() {

    }

    public JPanel getPanel(Graphics graphics) {

        for (int i  = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (sapperController.getFieldStatus(i, j) != FieldStatus.OPENED) {
                    graphics.drawImage()
                }
            }
        }
    }

    @Override
    public void setController(SapperController sapperController) {
        this.sapperController = sapperController;
    }

    @Override
    public void showMessage(String message) {

    }
}
