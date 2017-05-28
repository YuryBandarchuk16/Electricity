package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public abstract class UnaryOperation implements Operand {

    protected Operand firstOperand;

    public UnaryOperation(Operand firstOperand) {
        this.firstOperand = firstOperand;
    }

    @Override
    public double evaluate(double x) {
        return operation(firstOperand.evaluate(x));
    }

    @Override
    public int evaluate(int x) throws OverflowException, DivisionByZeroException {
        return operation(firstOperand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException, DivisionByZeroException {
        return operation(firstOperand.evaluate(x, y, z));
    }

    protected abstract double operation(double firstValue);

    protected abstract int operation(int firstValue) throws OverflowException;

}
