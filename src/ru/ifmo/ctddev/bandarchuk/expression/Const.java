package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Const implements Operand {
    private double value;

    public static Const ONE = new Const(1);
    public static Const TWO = new Const(2);
    public static Const ZERO = new Const(0);

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
    public double evaluate(double x, double y, double z) {
        return value;
    }

    @Override
    public Operand diff(String name) {
        return Const.ZERO;
    }
}
