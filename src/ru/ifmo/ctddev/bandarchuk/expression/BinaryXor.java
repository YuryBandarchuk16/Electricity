package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/29/17.
 */
public class BinaryXor extends BinaryOperation {

    public BinaryXor(Operand firstOpeand, Operand secondOperand) {
        super(firstOpeand, secondOperand);
    }

    @Override
    protected double operation(double firstValue, double secondValue) {
        return Double.NaN;
    }

    @Override
    protected int operation(int firstValue, int secondValue) {
        return (firstValue ^ secondValue);
    }


}
