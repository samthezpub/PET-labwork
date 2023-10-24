package org.example.Interface;

import javax.swing.*;

public class About {
    private boolean isVisible = false;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public About() {
        JFrame about = new JFrame("About");
        int x = 700;
        int y = 500;
        // Загрузка изображения
        ImageIcon imageIcon = new ImageIcon("src/main/resources/about/about.png");

        // Создание JLabel с изображением
        JLabel imageLabel = new JLabel(imageIcon);
        about.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight() + 40);
        about.setResizable(false);


        about.add(imageLabel);

        about.setVisible(true); //Otherwise the frame will not show up
    }
}
