package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Sin extends UnaryOperation {

    public Sin(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected double operation(double firstValue) {
        return Math.sin(firstValue);
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        return 0;
    }

    @Override
    protected Operand diffOp(Operand first, Operand leftDiff) {
        return new CheckedMultiply(new Sin(first), leftDiff);
    }
}
