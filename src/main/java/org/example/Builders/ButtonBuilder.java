package org.example.Builders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Класс служит для того чтобы склепать свою кнопку по частям,
 * можно было сделать и по конструктору, но тут слишком много параметров
 * @param <T> женерик для Action'ов (событий при нажатии кнопки)
 */
public class ButtonBuilder<T> {

    JButton button = new JButton();

    /**
     * Добавляет надпись на кнопке
     * @param text String надпись на кнопке
     */
    public void addText(String text){
        button.setText(text);
    }

    /**
     * Устанавливает размеры кнопки
     * @param width int ширина
     * @param height int высота
     */
    public void addSize(int width, int height){
        button.setSize(width, height);
    }

    /**
     * Устанавливает координаты кнопки на форме
     * @param x int X (пикселей по горизонтали)
     * @param y int Y (пикселей по вертикали)
     */
    public void addPosition(int x, int y){
        button.setLocation(x,y);
    }

    /**
     * Устанавливает что кнопка будет делать при нажатии
     * @param action действие при нажатии
     *
     * @see org.example.MainForm.ExitAction пример действия
     */
    public void addAction(T action){
        button.addActionListener((ActionListener) action);
    }

    /**
     * Возвращает нашу готовую кнопочку
     * @return JButton
     */
    public JButton build(){
        return button;
    }

}