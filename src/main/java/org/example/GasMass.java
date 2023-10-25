package org.example;

public enum GasMass {

    ZeroOne(0.1F),
    ZeroTwo(0.2F),
    ZeroThree(0.3F),
    ZeroFour(0.4F),
    ZeroFive(0.5F),
    ZeroSix(0.6F);


    private float mass;


    GasMass(float mass) {this.mass = mass;}

    public float getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return "GasMass{" +
                "mass=" + mass +
                '}';
    }
}
