package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/5/17.
 */
public class Sqrt extends UnaryOperation {

    private static final int MAX_SQUARED_IN_INTEGER = 46340;

    public Sqrt(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand leftDiff) {
        return new Divide(leftDiff, new Multiply(Const.TWO, first));
    }

    @Override
    protected double operation(double firstValue) {
        return firstValue;
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        if (firstValue < 0) {
            throw new OverflowException("impossible to take sqrt of negative number" + firstValue);
        }
        int le = 0;
        int ri = MAX_SQUARED_IN_INTEGER;
        while (ri - le > 1) {
            int mid = (le + ri) >> 1;
            if (mid * mid <= firstValue) {
                le = mid;
            } else {
                ri = mid;
            }
        }
        return le;
    }
}