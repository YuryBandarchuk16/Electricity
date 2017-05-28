package sample.physics;


import ru.ifmo.ctddev.bandarchuk.expression.*;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Field {

    private Operand parsedExpression;

    private Operand diffX;
    private Operand diffY;
    private Operand diffZ;


    public Field(String expression) throws OverflowException, IncorrectExpressionException {
        ExpressionParser expressionParser = new ExpressionParser();
        parsedExpression = expressionParser.parse(expression);
        diffX = parsedExpression.diff("x");
        diffY = parsedExpression.diff("y");
        diffZ = parsedExpression.diff("z");
    }

    public double getPhiAt(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return parsedExpression.evaluate(x, y, z);
    }

    private double getVectorLength(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getEAt(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return getVectorLength(diffX.evaluate(x, y, z), diffY.evaluate(x, y, z), diffZ.evaluate(x, y, z));
    }

}
