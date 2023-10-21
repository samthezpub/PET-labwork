package org.example;

public class Math {
    public static double calculateA(int temp, double mass, GasType gasType){
        double volume = 1; // объём, в литрах

        // плотность, в г/м3, зависит от типа газа
        double density = switch (GasType.valueOf(String.valueOf(gasType))) {
            case Nitrogen -> 1.2506;
            case Argon -> 1.784;
            case Oxygen -> 1.429;
            case Neon -> 0.9002;
            case Fluorine -> 1.696;
            case NitrogenOxide -> 1.2506;
            case CarbonOxide -> 1.165;
            default -> 0;
        };

        double result = 0;
        return result; // я обязательно сделаю вывод сразу в return, без это переменной
    }
}
