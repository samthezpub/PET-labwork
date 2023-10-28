package org.example.Forms;

import org.example.Builders.ButtonBuilder;
import org.example.Exceptions.ValueNotSelectedException;
import org.example.ExperimentMath;
import org.example.Enums.GasType;
import org.example.Interface.IForm;
import org.example.Models.ExperimentEntity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm {


    private final JFrame mainMenu = new JFrame("Главное меню");
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final JProgressBar progressBar;

    private final JPanel labelOptionalInfo;

    private Settings settings = new Settings();
    private GasType gasType;


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



        /*
        Шкала
         */
        BoundedRangeModel model = new DefaultBoundedRangeModel(50, 0, 0, 300);

        progressBar = new JProgressBar(model);
        progressBar.setStringPainted(true);

        JSlider slider = new JSlider(model);



        JPanel experimentButtonsPanel = new JPanel(new FlowLayout());

        final Thread experimentThread = null;
        ThreadProvider threadProvider = new ThreadProvider();

        ButtonBuilder startExperiment = new ButtonBuilder("Начать");
        startExperiment.addSize(150, 25);

        JButton startExperimentButton = startExperiment.build();
        startExperimentButton.setEnabled(false);

        ButtonBuilder stopExperiment = new ButtonBuilder("Стоп");
        stopExperiment.addSize(150, 25);

        JButton stopExperimentButton = stopExperiment.build();
        stopExperimentButton.setEnabled(false);
        startExperimentButton.addActionListener(new ActionListener() {

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                stopExperimentButton.setEnabled(true);
                startExperimentButton.setEnabled(false);
                slider.setEnabled(false);

                threadProvider.startExperimentThread(experimentThread);
            }
        });
        stopExperimentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopExperimentButton.setEnabled(false);
                startExperimentButton.setEnabled(true);
                slider.setEnabled(true);

                threadProvider.stopExperimentThread(experimentThread);
            }
        });

        settingsMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings = new Settings();
                settings.show();
                if (settings.getSelectedVolume() != null && settings.getSelectedGas() != null) {
                    gasType = settings.getSelectedGas();
                    changeLabels(labelOptionalInfo);
                    startExperimentButton.setEnabled(true);
                }

            }
        });

        experimentButtonsPanel.add(startExperimentButton);
        experimentButtonsPanel.add(stopExperimentButton);



        model.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (progressBar.getValue() == progressBar.getMaximum()){
                    stopExperimentButton.setEnabled(false);
                    startExperimentButton.setEnabled(true);
                }
                progressBar.setValue(model.getValue());
                slider.setValue(model.getValue());
                changeLabels(labelOptionalInfo);
            }
        });

        JPanel progressBarPanel = new JPanel(new FlowLayout());
        progressBarPanel.add(progressBar);
        progressBarPanel.add(slider);

        mainPanel.add(progressBarPanel, BorderLayout.SOUTH);


        // Создаем панель для элементов в EAST
        JPanel eastPanel = new JPanel(new GridLayout(3, 1, 0, 3)); // 3 строки, 1 столбец

        // Добавляем элементы в панель для EAST
        eastPanel.add(settingsMenuOpen);
        eastPanel.add(aboutMenuOpen);
        eastPanel.add(exitButtonMenu);

        // Добавляем панель с элементами в EAST
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(experimentButtonsPanel, BorderLayout.NORTH);


        labelOptionalInfo = new JPanel(new GridLayout(5, 1));
        labelOptionalInfo.setLayout(new BoxLayout(labelOptionalInfo, BoxLayout.Y_AXIS));
        labelOptionalInfo.setPreferredSize(new Dimension(400, 200));

        mainPanel.add(labelOptionalInfo, BorderLayout.WEST);

        mainMenu.add(mainPanel);

        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);



        /*
            Настройка цветов формы
         */
        setupStylesPanels(eastPanel, experimentButtonsPanel, progressBarPanel);
        setupStylesButtons(aboutMenuOpen,settingsMenuOpen,exitButtonMenu,startExperimentButton,stopExperimentButton);
        setupProgressBar(slider);
        
    }

    public void setupProgressBar(JSlider slider){
        slider.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        slider.setForeground(Color.WHITE);

        progressBar.setBackground(Color.darkGray);
        progressBar.setForeground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);

        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
    }
    public void setupStylesPanels(JPanel eastPanel, JPanel experimentButtonsPanel, JPanel panel){
        mainPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        eastPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        labelOptionalInfo.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f)); // боковая панель с цифрами
        experimentButtonsPanel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
        panel.setBackground(Color.getHSBColor(6.5f, 0.30f, 0.6f));
    }

    public void setupStylesButtons(JButton aboutMenuOpen, JButton settingsMenuOpen, JButton exitButtonMenu, JButton startExperimentButton, JButton stopExperimentButton) {
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

        startExperimentButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        startExperimentButton.setForeground(Color.WHITE);
        startExperimentButton.setFocusPainted(false);
        startExperimentButton.setBorderPainted(false);

        stopExperimentButton.setBackground(Color.getHSBColor(6.5f, 0.4f, 0.5f));
        stopExperimentButton.setForeground(Color.WHITE);
        stopExperimentButton.setFocusPainted(false);
        stopExperimentButton.setBorderPainted(false);

    }

    public void setupStylesLabels(JLabel molarMass, JLabel gas, JLabel value, JLabel gasConstant, JLabel weight, JLabel density, JLabel temperature){
        molarMass.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        gas.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        value.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        gasConstant.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        weight.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        density.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        temperature.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
    }

    /**
     * @param labelOptionalInfo наш panel со значениями
     * @see ExperimentMath класс для вычисления формулы
     */
    public void changeLabels(JPanel labelOptionalInfo) {

        try {
            if (settings.getSelectedGas().equals(null) || settings.getSelectedVolume().equals(null)) {
                throw new ValueNotSelectedException("Вы не выбрали значение!");
            }
        } catch (ValueNotSelectedException e) {
            return;
        }


        // Очищаем панель перед использованием
        labelOptionalInfo.removeAll();

        // Вычисляем по формуле
        ExperimentEntity experiment = ExperimentMath.calculate(progressBar.getValue(), gasType, Double.valueOf(settings.getSelectedVolume()), Float.parseFloat(settings.getSelectedVolume())); // Тестовое, пока нет форм с выбором



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

        setupStylesLabels(molarMass,gas,value,gasConstant,weight,density,temperature);

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


    class ThreadProvider{
        private boolean isRunning = false;

        // Метод для старта потока
        public void startExperimentThread(Thread experimentThread) {
            if(!isRunning && progressBar.getValue() == progressBar.getMaximum()){
                progressBar.setValue(0);
                isRunning = true;
                experimentThread = getExperimentThread();
                experimentThread.start();
            } else if (!isRunning) {
                isRunning = true;
                experimentThread = getExperimentThread();
                experimentThread.start();
            }
        }

        // Метод для остановки потока
        public void stopExperimentThread(Thread experimentThread) {
            isRunning = false;
            if (experimentThread != null && experimentThread.isAlive()) {
                experimentThread.interrupt();
            }
        }

        /**
         * Метод создаёт Thread и возвращает его
         * @return Thread
         */
        public Thread getExperimentThread() {
            Thread t1 = new Thread(() -> {
                int min = progressBar.getValue();
                Thread thread = Thread.currentThread();
                try {
                    for (int i = min; i <= progressBar.getMaximum(); i++) {
                        if (!isRunning || thread.isInterrupted()){
                            break;
                        }
                        Thread.sleep(100);

                        progressBar.setValue(progressBar.getValue() + 1);
                        changeLabels(labelOptionalInfo);
                    }
                } catch (Exception e) {

                } finally {
                    isRunning = false; // Устанавливаем флаг в false при завершении работы потока
                }
            });
            return t1;
        }

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
