package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by Viktoria on 5/28/17.
 */
public class Tan extends UnaryOperation {

    public Tan(Operand firstOperand) {
        super(firstOperand);
    }

    @Override
    protected double operation(double firstValue) {
        return Math.tan(firstValue);
    }

    @Override
    protected int operation(int firstValue) throws OverflowException {
        return 0;
    }

    @Override
    protected Operand diffOp(Operand first, Operand leftDiff) {
        return new CheckedDivide(leftDiff, new Square(new Cos(first)));
    }
}
