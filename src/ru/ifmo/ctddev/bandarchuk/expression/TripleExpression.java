package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface TripleExpression {
    int evaluate(int x, int y, int z) throws OverflowException, DivisionByZeroException;
}
