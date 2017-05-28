package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/5/17.
 */

public class Max extends BinaryOperation {

    public Max(Operand firstOpeand, Operand secondOperand) {
        super(firstOpeand, secondOperand);
    }

    @Override
    protected double operation(double firstValue, double secondValue) {
        if (firstValue > secondValue) {
            return firstValue;
        } else {
            return secondValue;
        }
    }

    @Override
    protected int operation(int firstValue, int secondValue) {
        if (firstValue > secondValue) {
            return firstValue;
        } else {
            return secondValue;
        }
    }

}
