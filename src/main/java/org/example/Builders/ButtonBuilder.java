package org.example.Builders;

import org.example.Forms.MainForm;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Класс служит для того чтобы склепать свою кнопку по частям,
 * можно было сделать и по конструктору, но тут слишком много параметров
 * @param <T> женерик для Action'ов (событий при нажатии кнопки)
 */
public class ButtonBuilder<T> {

    JButton button = new JButton();

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
     * @see MainForm.ExitAction пример действия
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

    // Функция для создания текста на кнопке теперь конструктор
    public ButtonBuilder(String text) {
        button.setText(text);
    }
}
