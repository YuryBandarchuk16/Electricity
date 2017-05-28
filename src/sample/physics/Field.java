package sample.physics;


import ru.ifmo.ctddev.bandarchuk.expression.*;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Field {

    private Operand expression;
    private ExpressionParser expressionParser;

    private Operand diffX;
    private Operand diffY;
    private Operand diffZ;


    public Field(String expression) throws OverflowException, IncorrectExpressionException {
        this.expressionParser = new ExpressionParser();
        this.expression = this.expressionParser.parse(expression);
        this.diffX = this.expression.diff("x");
        this.diffY = this.expression.diff("y");
        this.diffZ = this.expression.diff("z");
    }

    public double getPhiAt(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return this.expression.evaluate(x, y, z);
    }

    private double getVectorLength(double xComponent, double yComponent, double zComponent) {
        return Math.sqrt(xComponent * xComponent + yComponent * yComponent + zComponent * zComponent);
    }

    public double getEAt(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        return getVectorLength(diffX.evaluate(x, y, z), diffY.evaluate(x, y, z), diffZ.evaluate(x, y, z));
    }

}
