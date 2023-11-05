package com.example.labworkjavafx.Models;

public class MainVariablesData {
    private String variable;
    private double value;
    private String si;

    public MainVariablesData(String variable, String si) {
        this.variable = variable;
        this.value = 0.0;
        this.si = si;
    }

    public MainVariablesData(String variable, double value, String si) {
        this.variable = variable;
        this.value = value;
        this.si = si;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }
}
