package ru.ifmo.ctddev.bandarchuk.expression;

public class Main {

    public static void main(String[] args) throws IncorrectExpressionException, OverflowException, DivisionByZeroException {
        double val = new ExpressionParser().parse("sin(x + y)").evaluate(Math.PI * 0.5, 0.0, 0.0);
        System.out.println(val);
    }
}
