package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperimentEntity {
    private double molarMass;
    private String gasName;
    private double volume;
    private float gasConstantA;
    private float gasConstantB;
    private float gasConstantR;
    private float weight;
    private double pressure;
    private int temperature;
}
