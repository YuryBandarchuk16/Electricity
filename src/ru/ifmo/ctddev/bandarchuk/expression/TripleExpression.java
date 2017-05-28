package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface TripleExpression {
    double evaluate(double x, double y, double z) throws OverflowException, DivisionByZeroException;
}
