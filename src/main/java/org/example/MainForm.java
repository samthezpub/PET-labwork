package org.example;

import org.example.Builders.ButtonBuilder;
import org.example.Interface.About;
import org.example.Interface.IForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm {

    private JFrame mainMenu = new JFrame("Главное меню");
    private JPanel buttonPanel = new JPanel(null);

    public MainForm() {
        int x = 700;
        int y = 500;
        mainMenu.setSize(x, y);
        mainMenu.setResizable(false);


        ButtonBuilder exitButton = new ButtonBuilder("Выйти"); // Короч мой класс для создания кнопок
        exitButton.addSize(150, 50); // добавить размер
        exitButton.addPosition(10, 400); // установить позицию
        exitButton.addAction(new ExitAction()); // установить то что будет при нажатии

        JButton exitButtonMenu = exitButton.build(); // build() возвращает готовую кнопку учитывая установленные ранее значения

        buttonPanel.add(exitButtonMenu);
        addToFrame(buttonPanel);


        mainMenu.setVisible(true); // делаем форму видимой
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // при закрытии программа закрывается

        /*
           аналогичное создание кнопки по шаблону выше
        */
        ButtonBuilder aboutMenu = new ButtonBuilder("О программе");
        aboutMenu.addPosition(10, 340);
        aboutMenu.addSize(150, 50);
        
        JButton aboutMenuOpen = aboutMenu.build();

        buttonPanel.add(aboutMenuOpen);
        addToFrame(buttonPanel);

        /*
            действие по нажатию кнопки (прошлый вариант мне не понравился)
            Инициализирует форму About и делает её видимой
        */
        aboutMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About about = new About();
                about.setVisible(true);
            }
        });

        ButtonBuilder settingsMenu = new ButtonBuilder("Настройки");
        settingsMenu.addPosition(10, 280);
        settingsMenu.addSize(150, 50);

        JButton settingsMenuOpen = settingsMenu.build();

        buttonPanel.add(settingsMenuOpen);
        addToFrame(buttonPanel);

        settingsMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skala skala = new Skala();
                skala.setVisible(true);
            }
        });

        mainMenu.setVisible(true); // делаем главное меню видимым
    }




    /**
     * Этот метод добавляет элемент в форму
     *
     * @param object Любой объект форм
     * @see Component компонент, от которого наследуются все объекты форм
     */
    @Override
    public void addToFrame(Component object) {
        mainMenu.add(object);
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
