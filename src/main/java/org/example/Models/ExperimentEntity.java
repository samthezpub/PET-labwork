package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperimentEntity {
    private String molarMass;
    private String gasName;
    private String volume;
    private String gasConstantA;
    private String gasConstantB;
    private String gasConstantR;
    private String weight;
    private String pressure;
    private int temperature;
}
