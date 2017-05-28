package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }


    @Override
    public int operation(int x, int y) throws OverflowException {
        if (x == 0 || y == 0) {
            return 0;
        }
        boolean condition = true;
        if (x >= 0 && y >= 0) {
            condition = (x <= Integer.MAX_VALUE / y);
        } else if (x <= 0 && y <= 0) {
            condition = (x >= Integer.MAX_VALUE / y);
        } else if (x <= 0 && y >= 0) {
            condition = (x >= Integer.MIN_VALUE / y);
        } else if (x >= 0 && y <= 0) {
            condition = (y >= Integer.MIN_VALUE / x);
        }
        if (condition == false) {
            throw new OverflowException("on multiplying " + x + " by " + y);
        }
        return x * y;
    }

    @Override
    public double operation(double x, double y) {
        return x * y;
    }


}
