package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface Expression  {
    int evaluate(int x) throws OverflowException, DivisionByZeroException;
}
