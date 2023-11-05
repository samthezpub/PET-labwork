package com.example.labworkjavafx.Models;

public class MainVariablesData {
    private String variable;
    private String value;
    private String si;

    public MainVariablesData(String variable, String si) {
        this.variable = variable;
        this.value = String.valueOf(0.0);
        this.si = si;
    }

    public MainVariablesData(String variable, String value, String si) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }
}
