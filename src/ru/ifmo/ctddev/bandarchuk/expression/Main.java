package ru.ifmo.ctddev.bandarchuk.expression;

public class Main {

    public static void main(String[] args) throws IncorrectExpressionException, OverflowException, DivisionByZeroException {
        //String expression = "x min 5 max 3";
        System.out.println(579577008 - -1598158351);
//        System.out.println();
        String expression = "abb 1";
        //String expression = "abs (-579577008 min - 924610065 + 1941300832)";
        int ans = 0;
        ans = new ExpressionParser().parse(expression).evaluate(-1598158351, -1957350440, 8058307);
        System.out.println(ans);/*
        System.out.println("x         f");
        for (int i = 0; i <= 10; i++) {
            if (i == 10) {
                System.out.print(i + "        ");
            } else {
                System.out.print(i + "         ");
            }
            try {
                int result = new ExpressionParser().parse(expression).evaluate(i, 0, 0);
                System.out.println(result);
            } catch (OverflowException e) {
                System.out.println("overflow");
            } catch (DivisionByZeroException e) {
                System.out.println("division by zero");
            } catch (IncorrectExpressionException e) {
                System.out.println("incorrect expression");
            }
        }*/
    }
}
