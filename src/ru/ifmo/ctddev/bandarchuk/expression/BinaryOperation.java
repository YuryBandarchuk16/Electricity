package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public abstract class BinaryOperation implements Operand {

    protected Operand firstOperand, secondOperand;

    public BinaryOperation(Operand firstOperand, Operand secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public double evaluate(double x) {
        return operation(firstOperand.evaluate(x), secondOperand.evaluate(x));
    }

    @Override
    public int evaluate(int x) throws OverflowException, DivisionByZeroException {
        return operation(firstOperand.evaluate(x), secondOperand.evaluate(x));
    }

    @Override
    public double evaluate(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return operation(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }

    @Override
    public Operand diff(String name) {
        return diffOp(firstOperand, secondOperand, firstOperand.diff(name), secondOperand.diff(name));
    }

    protected abstract Operand diffOp(Operand first, Operand second,
                                               Operand leftDiff, Operand rightDiff);

    protected abstract double operation(double firstValue, double secondValue);

    protected abstract int operation(int firstValue, int secondValue) throws OverflowException, DivisionByZeroException;

}
