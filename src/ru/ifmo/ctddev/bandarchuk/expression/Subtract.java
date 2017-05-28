package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Subtract extends BinaryOperation {

    public Subtract(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected Operand diffOp(Operand first, Operand second, Operand leftDiff, Operand rightDiff) {
        return new Subtract(leftDiff, rightDiff);
    }

    @Override
    public int operation(int x, int y) {
        return x - y;
    }

    @Override
    public double operation(double x, double y) {
        return x - y;
    }

}