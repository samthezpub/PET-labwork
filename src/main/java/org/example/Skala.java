package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Skala {
    public Skala () {
        JFrame frame = new JFrame("Bounded Range Model Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

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
        panel.add(incrementButton);
        panel.add(decrementButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
