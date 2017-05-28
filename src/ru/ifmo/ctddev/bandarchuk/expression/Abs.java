package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/29/17.
 */
public class Abs extends UnaryOperation {

    public Abs(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand leftDiff) {
        return new CheckedMultiply(new CheckedDivide(first, new Abs(first)), leftDiff);
    }

    @Override
    protected double operation(double firstValue) {
        return Math.abs(firstValue);
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        if (firstValue == Integer.MIN_VALUE) {
            throw new OverflowException("impossible to take abs of " + firstValue);
        }
        return Math.abs(firstValue);
    }
}
