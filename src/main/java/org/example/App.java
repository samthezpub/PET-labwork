package org.example;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        MainForm mainForm = new MainForm();

        {
            JFrame main = new JFrame("About");
            int x = 700;
            int y = 500;
            // Загрузка изображения
            ImageIcon imageIcon = new ImageIcon("src/main/resources/about/about.png");

// Создание JLabel с изображением
            JLabel imageLabel = new JLabel(imageIcon);
            main.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight() + 40);
            main.setResizable(false);


            main.add(imageLabel);

            main.setVisible(true); //Otherwise the frame will not show up
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }
}
