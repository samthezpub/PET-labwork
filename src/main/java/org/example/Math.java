package org.example;

public class Math {
    public static double calculateA(double volume, int temp, double mass, GasType gasType){
        double density; // плотность

        switch (GasType.valueOf(String.valueOf(gasType))){
            case Nitrogen:
                density = 1.2506;
                break;
            case Argon:
                density = 1.784;
                break;
            case Oxygen:
                density = 1.429;
                break;
            case Neon:
                density = 0.9002;
                break;
            case Fluorine:
                density = 1.696;
                break;
            case NitrogenOxide:
                density = 1.2506;
                break;
            case CarbonOxide:
                density = 1.165;
                break;
            default:
                density = 0;
        }

        double result = 0;
        return result; // я обязательно сделаю вывод сразу в return, без это переменной
    }
}
