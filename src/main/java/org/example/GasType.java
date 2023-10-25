package org.example;

public enum GasType {
    Nitrogen("Азот", 14.000F),
    Argon("Аргон",39.948F ),
    Oxygen("Кислород", 15.999F),
    Neon("Неон", 20.179F),
    Fluorine("Фтор", 18.998F),
    NitrogenOxide("Оксид азота", 44.012F),
    CarbonOxide("Оксид углерода", 44.010F);



    private String title;
    private float mass;

    GasType(String title, float mass) {this.title = title; this.mass = mass;}

    @Override
    public String toString() {
        return "GasType{" +
                "title='" + title + '\'' +
                ", mass=" + mass +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public float getMass() {
        return mass;
    }
}
