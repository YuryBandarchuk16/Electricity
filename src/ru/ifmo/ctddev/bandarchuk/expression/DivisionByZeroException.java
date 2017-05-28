package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/1/17.
 */
public class DivisionByZeroException extends Exception {

    public DivisionByZeroException(String message) {
        super("division by zero: " + message);
    }
}
