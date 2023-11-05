package com.example.labworkjavafx.Utils;

import com.example.labworkjavafx.Enums.GasTypeEnum;
import com.example.labworkjavafx.Models.ExperimentEntity;

import java.text.DecimalFormat;


public class ExperimentMath {
    public static ExperimentEntity calculate(GasTypeEnum gasType, double volume, int temp, String roundFormat) {
        roundFormat = switch ((String) roundFormat) {
            case "2 знака после запятой" -> "#.##";
            case "3 знака после запятой" -> "#.###";
            case "4 знака после запятой" -> "#.####";
            case "5 знаков после запятой" -> "#.#####";
            default -> "#.############";
        };
    /*
               Шаблон для округления
    */
        final DecimalFormat df = new DecimalFormat(roundFormat);


        // Универсальная газовая постоянная в системе SI
        float R = 8.314462618F; // (Дж / моль * К)

        float a = gasType.getA(); // Константа a
        float b = gasType.getB(); // Константа b
        float n = gasType.getMass(); // Количество молей
        int T = temp; // Температура
        double P = ((n * R * T) / (volume - n * b)) - (n * n * a) / (volume * volume); //

        ExperimentEntity experimentEntity = new ExperimentEntity(
                df.format(gasType.getMass()),
                gasType.getTitle(),
                df.format(volume),
                df.format(a),
                df.format(b),
                df.format(R),
                String.valueOf(n),
                df.format(P),
                T
        );


        return experimentEntity;
    }
}
