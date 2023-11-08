package com.example.labworkjavafx.Enums;

public enum GasTypeEnum {
    Nitrogen("Азот", 14.000F,"N₂" , 1.390F, 0.039F),
    Argon("Аргон", 39.948F,"Ar" ,1.352F, 0.032F),
    Oxygen("Кислород", 15.999F, "O₂", 1.360F, 0.031F),
    Neon("Неон", 20.179F, "Ne" ,0.211F, 0.017F),
    Fluorine("Фтор", 18.998F, "F₂" ,1.097F, 0.030F),
    NitrogenOxide("Оксид азота", 29.006F, "NO" , 0.680F, 0.036F),
    CarbonOxide("Оксид углерода", 28.010F, "CO" ,0.365F, 0.042F);


    private String title;
    private float molarMass;
    private String chemical;
    private float A;
    private float B;

    GasTypeEnum(String title, float molarMass,String chemical, float a, float b) {
        this.title = title;
        this.molarMass = molarMass;
        this.chemical = chemical;
        A = a;
        B = b;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getChemical() {
        return chemical;
    }

    public float getMass() {
        return molarMass;
    }

    public float getA() {
        return A;
    }

    public float getB() {
        return B;
    }
}
