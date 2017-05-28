package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/27/17.
 */
public interface Parser {
   TripleExpression parse(String expression) throws OverflowException, IncorrectExpressionException;
}

