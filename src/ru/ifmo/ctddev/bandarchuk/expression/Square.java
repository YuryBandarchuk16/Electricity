package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/29/17.
 */
public class Square extends UnaryOperation {

    public Square(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand leftDiff) {
        return new CheckedMultiply(Const.TWO, new CheckedMultiply(first, leftDiff));
    }

    @Override
    protected double operation(double firstValue) {
        return firstValue * firstValue;
    }

    @Override
    protected int operation(int firstValue) {
        return firstValue * firstValue;
    }
}
