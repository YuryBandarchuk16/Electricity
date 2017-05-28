package sample.physics;


import ru.ifmo.ctddev.bandarchuk.expression.*;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Field {

    private TripleExpression expression;
    private ExpressionParser expressionParser;


    public Field(String expression) {
        this.expressionParser = new ExpressionParser();
        try {
            this.expression = this.expressionParser.parse(expression);
        } catch (IncorrectExpressionException e) {
            System.out.println("Incorrect expression, sorry :(");
            System.exit(0);
        } catch (OverflowException e) {
            System.out.println("overflow happened, sorry :(");
            System.exit(0);
        }
    }

    public double getPhiAt(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return this.expression.evaluate(x, y, z);
    }


}
