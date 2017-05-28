package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/1/17.
 */
public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected double operation(double firstValue) {
        return 0;
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        if (firstValue == Integer.MIN_VALUE) {
            throw new OverflowException("on trying to negate " + firstValue);
        }
        return -firstValue;
    }
}
