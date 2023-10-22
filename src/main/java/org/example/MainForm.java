package org.example;

import org.example.Builders.ButtonBuilder;
import org.example.Interface.IForm;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm {
    private JFrame frame = new JFrame("Main");
    private JPanel buttonPanel = new JPanel(null);

    public MainForm() {
        int x = 700;
        int y = 500;
        frame.setSize(x, y);
        frame.setResizable(false);


        ButtonBuilder buttonBuilder = new ButtonBuilder(); // Короч мой класс для создания кнопок
        buttonBuilder.addText("Выйти"); // добавить текст
        buttonBuilder.addSize(150, 50); // добавить размер
        buttonBuilder.addPosition(10, 400); // установить позицию
        buttonBuilder.addAction(new ExitAction()); // установить то что будет при нажатии

        JButton button = buttonBuilder.build(); // build() возвращает готовую кнопку учитывая установленные ранее значения

        buttonPanel.add(button);

        addToFrame(buttonPanel);


        frame.setVisible(true); //Otherwise the frame will not show up
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




    /**
     * Этот метод добавляет элемент в форму
     *
     * @param object Любой объект форм
     * @see Component компонент, от которого наследуются все объекты форм
     */
    @Override
    public void addToFrame(Component object) {
        frame.add(object);
    }

    class ExitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

        public ExitAction() {
        }
    }

}
