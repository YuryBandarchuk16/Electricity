package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Cos extends UnaryOperation {

    public Cos(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected double operation(double firstValue) {
        return Math.cos(firstValue);
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        return 0;
    }


    @Override
    public Operand diff(String name) {
        return null;
    }
}
