package ru.ifmo.ctddev.bandarchuk.expression;

public class Main {

    public static void main(String[] args) throws IncorrectExpressionException, OverflowException, DivisionByZeroException {
        Operand parsed = new ExpressionParser().parse("sin(x)");
        double val = parsed.diff("x").evaluate(0, 0, 0);
        System.out.println(val);
    }
}
