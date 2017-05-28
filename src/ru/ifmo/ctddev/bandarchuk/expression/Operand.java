package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface Operand extends DoubleExpression, TripleExpression, Expression {
    Operand diff(String name);
}
