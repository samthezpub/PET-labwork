package org.example;

public enum GasType {
    Nitrogen("Азот"),
    Argon("Аргон"),
    Oxygen("Кислород"),
    Neon("Неон"),
    Fluorine("Фтор"),
    NitrogenOxide("Оксид азота"),
    CarbonOxide("Оксид углерода");

    private String title;

    GasType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GasType{" +
                "title='" + title + '\'' +
                '}';
    }
}
