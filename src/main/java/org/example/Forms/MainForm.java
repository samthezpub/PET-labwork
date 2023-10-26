package org.example.Forms;

import org.example.Builders.ButtonBuilder;
import org.example.Interface.IForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm {

    private JFrame mainMenu = new JFrame("Главное меню");
    private JPanel mainPanel = new JPanel(new BorderLayout());


    public MainForm() {
        int x = 700;
        int y = 500;
        mainMenu.setSize(x, y);
        mainMenu.setResizable(false);

        ButtonBuilder exitButton = new ButtonBuilder("Выйти");
        exitButton.addSize(150, 50);
        exitButton.addPosition(10, 400);
        exitButton.addAction(new ExitAction());

        JButton exitButtonMenu = exitButton.build();

        ButtonBuilder aboutMenu = new ButtonBuilder("О программе");
        aboutMenu.addPosition(10, 340);
        aboutMenu.addSize(150, 50);

        JButton aboutMenuOpen = aboutMenu.build();

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

        settingsMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skala skala = new Skala();
                skala.setVisible(true);

            }
        });




        // Создаем панель для элементов в EAST
        JPanel eastPanel = new JPanel(new GridLayout(3, 1, 0, 3)); // 3 строки, 1 столбец

        // Добавляем элементы в панель для EAST
        eastPanel.add(settingsMenuOpen);
        eastPanel.add(aboutMenuOpen);
        eastPanel.add(exitButtonMenu);

        // Добавляем панель с элементами в EAST
        mainPanel.add(eastPanel, BorderLayout.EAST);



        mainMenu.add(mainPanel);

        JPanel labelOptionalInfo = new JPanel(new GridLayout(5, 1));
        labelOptionalInfo.setLayout(new BoxLayout(labelOptionalInfo, BoxLayout.Y_AXIS));
        labelOptionalInfo.setPreferredSize(new Dimension(200, 200));
        JPanel labelMainInfo = new JPanel(new BorderLayout());
        labelMainInfo.setLayout(new FlowLayout());
        setupLabels(labelOptionalInfo, labelMainInfo);
        mainPanel.add(labelOptionalInfo, BorderLayout.WEST);

        mainMenu.add(mainPanel);

        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);



        /*
            Настройка цветов формы
         */

        mainPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        eastPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));

        aboutMenuOpen.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        aboutMenuOpen.setForeground(Color.WHITE);
        aboutMenuOpen.setFocusPainted(false);
        aboutMenuOpen.setBorderPainted(false);

        settingsMenuOpen.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        settingsMenuOpen.setForeground(Color.WHITE);
        settingsMenuOpen.setFocusPainted(false);
        settingsMenuOpen.setBorderPainted(false);

        exitButtonMenu.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        exitButtonMenu.setForeground(Color.WHITE);
        exitButtonMenu.setFocusPainted(false);
        exitButtonMenu.setBorderPainted(false);

        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
    }


    public void setupLabels(JPanel labelOptionalInfo, JPanel labelMainInfo) {

        JLabel molarMass = new JLabel("Молярная масса: ");
        molarMass.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel gas = new JLabel("Газ: ");
        gas.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel value = new JLabel("V: ");
        value.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel gasConstant = new JLabel("R: ");
        gasConstant.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel weight = new JLabel("Вес: ");
        weight.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel density = new JLabel("P: ");
        density.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel temperature = new JLabel("T: ");
        temperature.setHorizontalAlignment(SwingConstants.CENTER);

        labelOptionalInfo.add(molarMass, BorderLayout.WEST);
        labelOptionalInfo.add(gas, BorderLayout.WEST);
        labelOptionalInfo.add(value, BorderLayout.WEST);
        labelOptionalInfo.add(gasConstant, BorderLayout.WEST);
        labelOptionalInfo.add(weight, BorderLayout.WEST);
        labelOptionalInfo.add(density);
        labelOptionalInfo.add(temperature);
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

    class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

        public ExitAction() {
        }
    }
}
