package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/1/17.
 */
public class OverflowException extends Exception {

    public OverflowException(String message) {
        super("Overflow: " + message);
    }
}
