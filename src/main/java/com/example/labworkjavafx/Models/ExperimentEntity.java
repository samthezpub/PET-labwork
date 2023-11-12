package com.example.labworkjavafx.Models;

public class ExperimentEntity {
    private String molarMass;
    private String gasName;
    private String volume;
    private String gasConstantA;
    private String gasConstantB;
    private String gasConstantR;
    private String weight;
    private String pressure;
    private String temperature;

    public ExperimentEntity(String molarMass, String gasName, String volume, String gasConstantA, String gasConstantB, String gasConstantR, String weight, String pressure, String temperature) {
        this.molarMass = molarMass;
        this.gasName = gasName;
        this.volume = volume;
        this.gasConstantA = gasConstantA;
        this.gasConstantB = gasConstantB;
        this.gasConstantR = gasConstantR;
        this.weight = weight;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public String getMolarMass() {
        return molarMass;
    }

    public void setMolarMass(String molarMass) {
        this.molarMass = molarMass;
    }

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getGasConstantA() {
        return gasConstantA;
    }

    public void setGasConstantA(String gasConstantA) {
        this.gasConstantA = gasConstantA;
    }

    public String getGasConstantB() {
        return gasConstantB;
    }

    public void setGasConstantB(String gasConstantB) {
        this.gasConstantB = gasConstantB;
    }

    public String getGasConstantR() {
        return gasConstantR;
    }

    public void setGasConstantR(String gasConstantR) {
        this.gasConstantR = gasConstantR;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
