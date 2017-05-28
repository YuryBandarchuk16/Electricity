package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int operation(int x, int y) throws DivisionByZeroException, OverflowException {
        if (y == 0) {
            throw new DivisionByZeroException(x + " was tried to divide by " + y);
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("while dividing " + x + " by " + y);
        }
        return x / y;
    }

    @Override
    public double operation(double x, double y) {
        return x / y;
    }
}
