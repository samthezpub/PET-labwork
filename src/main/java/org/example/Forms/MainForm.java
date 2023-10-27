package org.example.Forms;

import org.example.Builders.ButtonBuilder;
import org.example.Exceptions.ValueNotSelectedException;
import org.example.ExperimentMath;
import org.example.GasType;
import org.example.Interface.IForm;
import org.example.Models.ExperimentEntity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm {


    private JFrame mainMenu = new JFrame("Главное меню");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JProgressBar progressBar;

    private JPanel labelOptionalInfo;

    private Settings settings = new Settings();


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
                settings = new Settings();
                settings.show();
                setupLabels(labelOptionalInfo);
            }
        });

        /*
        Шкала
         */
        BoundedRangeModel model = new DefaultBoundedRangeModel(50, 0, 0, 100);

        progressBar = new JProgressBar(model);
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
                setupLabels(labelOptionalInfo);
            }
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(progressBar);
        panel.add(slider);

        mainPanel.add(panel, BorderLayout.SOUTH);

        // Стили для progressBara
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

        progressBar.setBackground(Color.darkGray);
        progressBar.setForeground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);



        // Создаем панель для элементов в EAST
        JPanel eastPanel = new JPanel(new GridLayout(3, 1, 0, 3)); // 3 строки, 1 столбец

        // Добавляем элементы в панель для EAST
        eastPanel.add(settingsMenuOpen);
        eastPanel.add(aboutMenuOpen);
        eastPanel.add(exitButtonMenu);

        // Добавляем панель с элементами в EAST
        mainPanel.add(eastPanel, BorderLayout.EAST);



        mainMenu.add(mainPanel);

        labelOptionalInfo = new JPanel(new GridLayout(5, 1));
        labelOptionalInfo.setLayout(new BoxLayout(labelOptionalInfo, BoxLayout.Y_AXIS));
        labelOptionalInfo.setPreferredSize(new Dimension(200, 200));

        mainPanel.add(labelOptionalInfo, BorderLayout.WEST);

        mainMenu.add(mainPanel);

        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);



        /*
            Настройка цветов формы
         */

        mainPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        eastPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        labelOptionalInfo.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f)); // боковая панель с цифрами

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

    /**
     *
     * @param labelOptionalInfo наш panel со значениями
     * @see ExperimentMath класс для вычисления формулы
     */
    public void setupLabels(JPanel labelOptionalInfo) {

        try {
            if (settings.getSelectedItem() == null){
                throw new ValueNotSelectedException("Вы не выбрали значение!");
            }
        } catch (ValueNotSelectedException e){
            return;
        }


        // Очищаем панель перед использованием
        labelOptionalInfo.removeAll();

        // Вычисляем по формуле
        ExperimentEntity experiment = ExperimentMath.calculate(progressBar.getValue(), GasType.Argon, settings.getSelectedItem(), settings.getSelectedItem()); // Тестовое, пока нет форм с выбором



        /*
           Этот блок отрисовывает нам текст с какими-то значениями
        */
        JLabel molarMass = new JLabel("Молярная масса: ≈ " + experiment.getMolarMass());
        JLabel gas = new JLabel("Газ: " + experiment.getGasName());
        JLabel value = new JLabel("V: ≈ " + experiment.getVolume());
        JLabel gasConstant = new JLabel("R: ≈ " + experiment.getGasConstantR());
        JLabel weight = new JLabel("Вес: ≈ " + experiment.getWeight());
        JLabel density = new JLabel("P: ≈ " + experiment.getPressure());
        JLabel temperature = new JLabel("T: " + experiment.getTemperature());

        labelOptionalInfo.add(molarMass, BorderLayout.WEST);
        labelOptionalInfo.add(gas, BorderLayout.WEST);
        labelOptionalInfo.add(value, BorderLayout.WEST);
        labelOptionalInfo.add(gasConstant, BorderLayout.WEST);
        labelOptionalInfo.add(weight, BorderLayout.WEST);
        labelOptionalInfo.add(density);
        labelOptionalInfo.add(temperature);

        // Перерисовываем панель
        labelOptionalInfo.revalidate();
        labelOptionalInfo.repaint();
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
