package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Divide extends BinaryOperation {

    public Divide(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand second, Operand leftDiff, Operand rightDiff) {
        return new Divide(new Subtract(new Multiply(second, leftDiff), new Multiply(first, rightDiff)), new Square(second));
    }

    @Override
    public int operation(int x, int y) {
        return x / y;
    }

    @Override
    public double operation(double x, double y) {
        return x / y;
    }
}
