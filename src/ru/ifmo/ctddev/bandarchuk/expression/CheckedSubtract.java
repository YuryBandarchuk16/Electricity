package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class CheckedSubtract extends BinaryOperation {

    public CheckedSubtract(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand second, Operand leftDiff, Operand rightDiff) {
        return new Subtract(leftDiff, rightDiff);
    }


    @Override
    public int operation(int x, int y) throws OverflowException {
        boolean condition = true;
        if (x <= 0 && y >= 0) {
            condition = (Integer.MIN_VALUE + y <= x);
        }
        if (x >= 0 && y <= 0) {
            condition = (x <= Integer.MAX_VALUE + y);
        }
        if (condition == false) {
            throw new OverflowException("on subtraction " + y + " from " + x);
        }
        return x - y;
    }

    @Override
    public double operation(double x, double y) {
        return x - y;
    }

}