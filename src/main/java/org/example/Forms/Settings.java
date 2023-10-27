package org.example.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Settings {
    private JDialog frame;
    private Float selectedItem;
    public static String selectedRound; // а вообще так делать нельзя, не повторяйте моих ошибок



    public Settings() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setTitle("Настройка");
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(new GridLayout(5, 1)); // Используем GridLayout для вертикального расположения элементов



        /*
            Список вариантов объёма
        */
        JLabel volumeLabel = new JLabel("Выберете объем:");
        panel.add(volumeLabel);

        String[] items = {"0.1", "0.2", "0.3", "0.4", "0.5", "0.6",};
        JComboBox<String> volumeComboBox = new JComboBox<>(items);
        panel.add(volumeComboBox);



        /*
            Настройка округления
        */
        JLabel roundLabel = new JLabel("Округление:");
        panel.add(roundLabel);

        String[] round = {"2 знака после запятой", "3 знака после запятой", "4 знака после запятой", "5 знаков после запятой", "отключить"};
        JComboBox<String> roundComboBox = new JComboBox<>(round);
        panel.add(roundComboBox);



        /*
            Панель с кнопками
        */
        JPanel buttonPanel = new JPanel(); // Создаем отдельную панель для кнопок
        panel.add(buttonPanel);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Отмена");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) volumeComboBox.getSelectedItem();

            /*
                       Шаблоны для округления

               Варианты:
                   "0.00" : 123.49 -> 123.50
                   "#.##" : 123.49 -> 123.5

               Можно также увеличить кол-во нулей после запятой!
            */
                selectedRound = switch ((String) roundComboBox.getSelectedItem()) {
                    case "2 знака после запятой" -> "0.00";
                    case "3 знака после запятой" -> "0.000";
                    case "4 знака после запятой" -> "0.0000";
                    case "5 знаков после запятой" -> "0.00000";
                    default -> "#.############";
                };

                selectedItem = Float.parseFloat(selected);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });



        /*
            Настройка цвета
         */
        panel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        buttonPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));

        okButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setBorderPainted(false);

        cancelButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);

        volumeComboBox.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        volumeComboBox.setForeground(Color.WHITE);

        roundComboBox.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        roundComboBox.setForeground(Color.WHITE);
    }

    public Float getSelectedItem() {
        return selectedItem;
    }
    public void setSelectedItem(float selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void show(){
        frame.setVisible(true);
    }
}
