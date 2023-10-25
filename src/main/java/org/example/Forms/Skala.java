package org.example.Forms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Skala {
    private boolean isVisible = false;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Skala () {
        JDialog frame = new JDialog();
        frame.setSize(300, 150);
        frame.setModal(true);
        frame.setTitle("Настройки");
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        BoundedRangeModel model = new DefaultBoundedRangeModel(50, 0, 0, 100);

        JProgressBar progressBar = new JProgressBar(model);
        progressBar.setStringPainted(true);

        JSlider slider = new JSlider(model);

        JButton incrementButton = new JButton("Большe");
        JButton decrementButton = new JButton("Меньше");

        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setValue(model.getValue() + 1);
            }
        });

        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setValue(model.getValue() - 1);
            }
        });

        model.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                progressBar.setValue(model.getValue());
                slider.setValue(model.getValue());
            }
        });

        JPanel panel = new JPanel();
        panel.add(progressBar);
        panel.add(slider);
        panel.add(decrementButton);
        panel.add(incrementButton);

        /*
            Настройка цвета
         */
        panel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));

        incrementButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        incrementButton.setForeground(Color.WHITE);
        incrementButton.setFocusPainted(false);
        incrementButton.setBorderPainted(false);

        decrementButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        decrementButton.setForeground(Color.WHITE);
        decrementButton.setFocusPainted(false);
        decrementButton.setBorderPainted(false);

        slider.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        slider.setForeground(Color.WHITE);

        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);

        /*
            Вывод формы
         */
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
