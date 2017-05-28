package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Multiply extends BinaryOperation {

    public Multiply(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }


    @Override
    public int operation(int x, int y) {
        return x * y;
    }

    @Override
    public double operation(double x, double y) {
        return x * y;
    }


}
