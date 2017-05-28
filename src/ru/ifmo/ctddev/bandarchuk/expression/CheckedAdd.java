package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/1/17.
 */
public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand second, Operand leftDiff, Operand rightDiff) {
        return new CheckedAdd(leftDiff, rightDiff);
    }

    @Override
    public int operation(int x, int y) throws OverflowException {
        boolean condition = true;
        if (x >= 0 && y >= 0) {
            condition = (x <= Integer.MAX_VALUE - y);
        } else if (x <= 0 && y <= 0) {
            condition = (x >= Integer.MIN_VALUE - y);
        }
        if (!condition) {
            throw new OverflowException("on adding " + x + " and " + y);
        }
        return x + y;
    }

    @Override
    public double operation(double x, double y) {
        return x + y;
    }
}
