package org.example;

import java.text.DecimalFormat;

import org.example.Forms.Settings;
import org.example.Models.ExperimentEntity;

public class ExperimentMath {
    public static ExperimentEntity calculate(int temp, GasType gasType, double volume, float weight) {
        String roundFormat = Settings.selectedRound;
    /*
               Шаблон для округления
    */
        final DecimalFormat df = new DecimalFormat(roundFormat);


        // Универсальная газовая постоянная в системе SI
        float R = 8.314462618F; // (Дж / моль * К)

        float a = gasType.getA(); // Константа a
        float b = gasType.getB(); // Константа b
        float n = weight / gasType.getMass(); // Количество молей
        int T = temp; // Температура
        double P = ((n * R * T) / (volume - n * b)) - (n * n * a) / (volume * volume); //

        ExperimentEntity experimentEntity = new ExperimentEntity(
                df.format(gasType.getMass()),
                gasType.getTitle(),
                df.format(volume),
                df.format(a),
                df.format(b),
                df.format(R),
                df.format(weight),
                df.format(P),
                T
        );


        return experimentEntity;// я обязательно сделаю вывод сразу в return, без этой переменной
        // Я тебе верю, бро
    }
}
