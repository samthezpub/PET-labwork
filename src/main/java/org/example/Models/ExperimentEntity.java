package org.example.Models;

import lombok.Data;
import org.example.GasType;

@Data
public class ExperimentEntity {
    private double molarMass;
    private GasType gas;
    private double volume;
    private double gasConstant;
    private float weight;
    private int density;
    private int temperature;
}
