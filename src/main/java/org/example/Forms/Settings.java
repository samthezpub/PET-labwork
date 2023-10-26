package org.example.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Settings {
    private JDialog frame;
    private float selectedItem;
    public Settings() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(new GridLayout(3, 1)); // Используем GridLayout для вертикального расположения элементов

        JLabel label = new JLabel("Выберете объем:");
        panel.add(label);

        String[] items = {"0.1", "0.2", "0.3", "0.4", "0.5", "0.6",};
        JComboBox<String> comboBox = new JComboBox<>(items);
        panel.add(comboBox);

        JPanel buttonPanel = new JPanel(); // Создаем отдельную панель для кнопок
        panel.add(buttonPanel);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Отмена");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                selectedItem = Float.parseFloat(selected);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Закрыть приложение при нажатии "Отмена"
            }
        });


    }

    public float getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(float selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void show(){
        frame.setVisible(true);
    }
}
