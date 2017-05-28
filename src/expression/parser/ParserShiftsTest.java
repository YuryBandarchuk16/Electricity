package expression.parser;

import ru.ifmo.ctddev.bandarchuk.expression.DivisionByZeroException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;

import java.util.Arrays;

/**
 * @author Georgiy Korneev
 */
public class ParserShiftsTest extends ParserTest {
    protected ParserShiftsTest() {
        levels.add(0, list(
                op("<<", (a, b) -> (int) a << (int) b),
                op(">>", (a, b) -> (int) a >> (int) b)
        ));

        tests.addAll(Arrays.asList(
                op("1 << 5 + 3", (x, y, z) -> 256L),
                op("x + y << z", (x, y, z) -> (int) (x + y) << (int) z),
                op("x * y << z", (x, y, z) -> (int) (x * y) << (int) z),
                op("x << y << z", (x, y, z) -> (int) x << (int) y << (int) z),
                op("1024 >> 5 + 3", (x, y, z) -> 4L),
                op("x + y >> z", (x, y, z) -> x + y >> z),
                op("x * y >> z", (x, y, z) -> x * y >> z),
                op("x >> y >> z", (x, y, z) -> x >> y >> z)
        ));
    }

    public static void main(final String[] args) throws OverflowException, DivisionByZeroException {
        new ParserShiftsTest().run();
    }
}