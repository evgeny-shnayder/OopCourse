package evgeniy_shnayder.view;

import evgeniy_shnayder.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class SwingView implements View {
    private Controller controller;
    private JLabel resultLabel;
    private JFrame frame;
    private int index1;
    private int index2;

    public SwingView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        frame = new JFrame("Конвертер температур");

        frame.setSize(600, 200);
        frame.setMinimumSize(new Dimension(570, 140));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        frame.setContentPane(panel);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        panel.setLayout(gridBagLayout);

        JLabel label1 = new JLabel("Выберите конвертируемую шкалу температур: ");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        frame.getContentPane().add(label1, gridBagConstraints);

        String[] temperatureList = {"Кельвина", "Фаренгейта", "Цельсия"};

        JComboBox<String> comboBox1 = new JComboBox<>(temperatureList);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel.add(comboBox1, gridBagConstraints);

        JLabel label3 = new JLabel("Введите значение выбранной температуры: ");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panel.add(label3, gridBagConstraints);

        JTextField textField = new JTextField(20);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        panel.add(textField, gridBagConstraints);

        JLabel label2 = new JLabel("Выберите, в какую шкалу температур конвертировать: ");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panel.add(label2, gridBagConstraints);

        JComboBox<String> comboBox2 = new JComboBox<>(temperatureList);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        panel.add(comboBox2, gridBagConstraints);

        JButton convertButton = new JButton("конвертировать");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        panel.add(convertButton, gridBagConstraints);

        convertButton.addActionListener(e -> {
            try {
                String temperatureText = textField.getText();
                double temperature = Double.parseDouble(temperatureText);
                index1 = comboBox1.getSelectedIndex();
                index2 = comboBox2.getSelectedIndex();

                controller.convertTemperature(temperature, index1, index2);
            } catch (NumberFormatException ex) {
                showError("Температура должна быть числом.");
            }
        });

        resultLabel = new JLabel();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        panel.add(resultLabel, gridBagConstraints);

        frame.setVisible(true);
    }

    @Override
    public void updateTemperature(double temperature) {
        resultLabel.setText("Температура по шкале " + (index2 == 0 ? "Кельвина" : index2 == 1 ? "Фаренгейта" : "Цельсия")
                + " равна: " + temperature);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
