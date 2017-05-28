package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Variable implements Operand {

    private String variableName;

    public Variable(String variableName) {
        this.variableName = variableName;
    }


    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double value) {
        return value;
    }

    @Override
    public double evaluate(double x, double y, double z) {
        switch (variableName) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }
}
