package org.example;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.example.Models.ExperimentEntity;

public class ExperimentMath {
    /*
               Шаблон для округления

       Варианты:
           "0.00" : 123.49 -> 123.50
           "#.##" : 123.49 -> 123.5

       Можно также увеличить кол-во нулей после запятой!
    */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // TODO сделать выбор формата округления в настройках



    public static ExperimentEntity calculate(int temp, GasType gasType, double volume, float weight) {


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
                df.format(n),
                df.format(P),
                T
        );


        return experimentEntity;// я обязательно сделаю вывод сразу в return, без этой переменной
        // Я тебе верю, бро
    }
}
