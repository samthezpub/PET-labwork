package org.example;

public class Math {
    public static double calculateA(double volume, int temp, double mass, GasType gasType){
        double destinity = 0;

        switch (GasType.valueOf(String.valueOf(gasType))){
            case Nitrogen:
                destinity = 0.0012506;
                break;
            case Argon:
                destinity = 0.001784;
                break;
            case Oxygen:
                destinity = 0.001429;
                break;
            case Neon:
                destinity = 0.0008999;
                break;
            case Fluorine:
                destinity = 0.001580;
                break;
            case NitrogenOxide:
                break;
            case CarbonOxide:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + GasType.valueOf(String.valueOf(gasType)));
        }

        double result = 0;
        return result; // я обязательно сделаю вывод сразу в return, без это переменной
    }
}
