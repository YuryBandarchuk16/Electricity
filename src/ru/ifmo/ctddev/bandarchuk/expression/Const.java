package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Const implements Operand {
    private double value;

    public Const(double value) {
        this.value = value;
    }


    @Override
    public int evaluate(int x) {
        return (int)value;
    }

    @Override
    public double evaluate(double value) {
        return this.value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)value;
    }
}
