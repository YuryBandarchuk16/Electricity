package ru.ifmo.ctddev.bandarchuk.expression;

import java.util.EnumSet;

/**
 * Created by YuryBandarchuk on 3/27/17.
 */
public class ExpressionParser implements Parser {

    private int index;
    private int prevStateIndex;
    private int number;
    private int balance;
    private State state;
    private String expression;
    private String variableName;

    private EnumSet<State> binaryOperations = EnumSet.of(State.MIN, State.MAX, State.BINARY_AND, State.BINARY_OR, State.BINARY_XOR, State.DIVIDE_SIGN,
                            State.MINUS_SIGN, State.PLUS_SIGN, State.TIMES_SIGN, State.SHIFT_LEFT, State.SHIFT_RIGHT);
    private EnumSet<State> unaryOperations = EnumSet.of(State.MINUS_SIGN, State.ABS, State.SQUARE, State.SQRT, State.SIN, State.COS, State.TAN);
    private EnumSet<State> operands = EnumSet.of(State.CONSTANT, State.VARIABLE, State.RIGHT_BRACKET);


    private void checkIncorrectOperand(State previousState, State currentState) throws IncorrectExpressionException {
        if (previousState == State.NONE && binaryOperations.contains(currentState) && currentState != State.MINUS_SIGN) {
            throw new IncorrectExpressionException("missing first operand at index " + (prevStateIndex) + getPrint(prevStateIndex));
        }
        if (previousState == State.LEFT_BRACKET && binaryOperations.contains(currentState) && currentState != State.MINUS_SIGN) {
            throw new IncorrectExpressionException("expected operand, but not found at index " + prevStateIndex + getPrint(prevStateIndex));
        }
        if (previousState == State.NONE || (currentState == State.MINUS_SIGN && binaryOperations.contains(previousState))) {
            return;
        }
        if (unaryOperations.contains(currentState) && (unaryOperations.contains(previousState) || binaryOperations.contains(previousState))) {
            return;
        }
        if (binaryOperations.contains(currentState) && !operands.contains(previousState) && previousState != State.LEFT_BRACKET) {
            throw new IncorrectExpressionException("expected operand, but not found at index " + prevStateIndex + getPrint(prevStateIndex));
        }
    }

    private String getPrint(int index) {
        int start = index - 10;
        start = Math.max(start, 0);
        StringBuilder stringBuilder = new StringBuilder("\n" + expression.substring(start, Math.min(index + 15, expression.length())) + "\n");
        for (int i = start; i < index; i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("^");
        return stringBuilder.toString();
    }

    private void checkEndOfTheExpression(State previousState, State currentState) throws IncorrectExpressionException {
        if (currentState == State.NONE) {
            if (binaryOperations.contains(previousState) || unaryOperations.contains(previousState)) {
                throw new IncorrectExpressionException("no last argument, expected operand, but found nothing at index " + prevStateIndex  + getPrint(prevStateIndex));
            }
            if (operands.contains(previousState) && index < expression.length()) {
                throw new IncorrectExpressionException("something strange found at position " + prevStateIndex + getPrint(prevStateIndex));
            }
        } else if (currentState == State.RIGHT_BRACKET) {
            if (!operands.contains(previousState)) {
                throw new IncorrectExpressionException("missing argument at position " + (prevStateIndex) + getPrint(prevStateIndex));
            }
        }
    }


    private void checkIncorrectOperation(State previousState, State state) throws IncorrectExpressionException {
        if (operands.contains(previousState) && !binaryOperations.contains(state) && state != State.NONE && state != State.RIGHT_BRACKET) {
            throw new IncorrectExpressionException("expected operation, but not found at index " + (prevStateIndex) + getPrint(prevStateIndex));
        }
    }


    private char currentChar() {
        if (index < expression.length()) {
            return expression.charAt(index);
        }
        return '@';
    }

    private char getNextNonWhiteSpaceCharacter() {
        while (Character.isWhitespace(currentChar())) {
            index++;
        }
        char result = currentChar();
        index++;
        return result;
    }

    private int getNextNumber(int addToStartIndex) throws OverflowException {
        int startIndex = index + addToStartIndex;
        while (Character.isDigit(currentChar())) {
            index++;
        }
        int result = 0;
        try {
            result = Integer.parseInt(expression.substring(startIndex, index));
        } catch (NumberFormatException e) {
            throw new OverflowException("too large constant " + expression.substring(startIndex, index) + " to fit in int at index " + startIndex  + getPrint(addToStartIndex));
        }
        return result;
    }

    private void makeUnknown() throws IncorrectExpressionException {
        throw new IncorrectExpressionException("illegal character or unknown function that starts with '" + expression.charAt(index) + "' at index " + index + getPrint(index));
    }

    private void makeUnknownAfterF(String func) throws IncorrectExpressionException {
        throw new IncorrectExpressionException("unknown function '" + func + "' at index " + index + getPrint(index));
    }
    private void checkAfter(int start) throws IncorrectExpressionException {
        if (index >= expression.length()) {
            return;
        }
        char nxt = '1';
        if (index < expression.length()) {
            nxt = expression.charAt(index);
        }
        if (index < expression.length() && (Character.isAlphabetic(nxt) || Character.isDigit(nxt))) {
            makeUnknownAfterF(expression.substring(start, index + 1));
        }
    }

    private void updateState() throws IncorrectExpressionException, OverflowException {
        prevStateIndex = index;
        char currentChar = getNextNonWhiteSpaceCharacter();
        State previousState = state;
        switch (currentChar) {
            case '+':
                state = State.PLUS_SIGN;
                break;
            case '-':
                if (!operands.contains(previousState) && index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    number = getNextNumber(-1);
                    state = State.CONSTANT;
                    break;
                }
                state = State.MINUS_SIGN;
                break;
            case '*':
                state = State.TIMES_SIGN;
                break;
            case '/':
                state = State.DIVIDE_SIGN;
                break;
            case '(':
                balance++;
                state = State.LEFT_BRACKET;
                break;
            case ')':
                balance--;
                if (balance < 0) {
                    throw new IncorrectExpressionException("wrong bracket sequence, no opening bracket for closing bracket at index " + (prevStateIndex) + getPrint(prevStateIndex));
                }
                state = State.RIGHT_BRACKET;
                break;
            case '&':
                state = State.BINARY_AND;
                break;
            case '|':
                state = State.BINARY_OR;
                break;
            case '^':
                state = State.BINARY_XOR;
                break;
            case '>':
                if (index + 1 >= expression.length() || expression.charAt(index + 1) != '>') {
                    throw new IncorrectExpressionException("expected > at index " + index + getPrint(index));
                }
                state = State.SHIFT_RIGHT;
                index++;
                break;
            case '<':
                if (index + 1 >= expression.length() || expression.charAt(index + 1) != '<') {
                    throw new IncorrectExpressionException("expected < at index " + index + getPrint(index));
                }
                state = State.SHIFT_LEFT;
                index++;
                break;
            case 'x':case 'y':case 'z':
                variableName = Character.toString(currentChar);
                state = State.VARIABLE;
                break;
            default:
                index--;
                if (Character.isDigit(currentChar)) {
                    number = getNextNumber(0);
                    state = State.CONSTANT;
                    break;
                }
                if (currentChar == 't') {
                    if (index + 2 < expression.length() && expression.substring(index, index + 3).equals("tan")) {
                        state = state.TAN;
                        index += 3;
                        break;
                    }
                }
                if (currentChar == 'a') {
                    if (index + 2 < expression.length() && expression.substring(index, index + 3).equals("abs")) {
                        state = State.ABS;
                        index += 3;
                        checkAfter(index - 3);
                        break;
                    }
                    makeUnknown();
                } else if (currentChar == 's') {
                    if (index + 5 < expression.length() && expression.substring(index, index + 6).equals("square")) {
                        state = State.SQUARE;
                        index += 6;
                        checkAfter(index - 3);
                        break;
                    }
                    if (index + 2 < expression.length() && expression.substring(index, index + 3).equals("sin")) {
                        state = State.SIN;
                        index += 3;
                        break;
                    }
                    if (index + 2 < expression.length() && expression.substring(index, index + 3).equals("cos")) {
                        state = State.COS;
                        index += 3;
                        break;
                    }
                    if (index + 4 < expression.length() && expression.substring(index, index + 4).equals("sqrt")) {
                        state = State.SQRT;
                        index += 4;
                        checkAfter(index - 3);
                        break;
                    }
                    makeUnknown();
                } else if (currentChar == 'm' && index + 2 < expression.length()
                        && (expression.substring(index, index + 3).equals("min") || expression.substring(index, index + 3).equals("max"))) {
                    if (expression.substring(index, index + 3).equals("min")) {
                        state = State.MIN;
                    } else if (expression.substring(index, index + 3).equals("max")) {
                        state = State.MAX;
                    }
                    index += 3;
                    checkAfter(index - 3);
                } else if (index < expression.length()) {
                    makeUnknown();
                } else {
                    state = State.NONE;
                }
                break;
        }
        checkIncorrectOperand(previousState, state);
        checkEndOfTheExpression(previousState, state);
        checkIncorrectOperation(previousState, state);
    }


    private Operand getNextFactor() throws IncorrectExpressionException, OverflowException {
        int wasIndex = index;
        updateState();
        Operand result;
        switch (state) {
            case CONSTANT:
                result = new Const(number);
                updateState();
                break;
            case VARIABLE:
                result = new Variable(variableName);
                updateState();
                break;
            case LEFT_BRACKET:
                result = parseTerms();
                if (state != State.RIGHT_BRACKET) {
                    throw new IncorrectExpressionException("wrong bracket sequence: closing bracket expected at index " + index  + getPrint(index));
                }
                updateState();
                break;
            case MINUS_SIGN:
                result = new CheckedNegate(getNextFactor());
                break;
            case PLUS_SIGN:
                result = getNextFactor();
                break;
            case ABS:
                result = new Abs(getNextFactor());
                break;
            case SQUARE:
                result = new Square(getNextFactor());
                break;
            case SQRT:
                result = new Sqrt(getNextFactor());
                break;
            case SIN:
                //
            default:
                throw new IncorrectExpressionException("no unary operation found in this part of expression: '" + expression.substring(wasIndex, index + 1) + "' at index " + index);
        }
        return result;
    }

    private Operand getNextTerm() throws IncorrectExpressionException, OverflowException {
        Operand currentFactor = getNextFactor();
        while (true) {
            switch (state) {
                case TIMES_SIGN:
                    currentFactor = new CheckedMultiply(currentFactor, getNextFactor());
                    break;
                case DIVIDE_SIGN:
                    currentFactor = new CheckedDivide(currentFactor, getNextFactor());
                    break;
                default:
                    return currentFactor;
            }
        }
    }

    private Operand parseTerms() throws IncorrectExpressionException, OverflowException {
        Operand currentTerm = getNextTerm();
        while (true) {
            switch (state) {
                case PLUS_SIGN:
                    currentTerm = new CheckedAdd(currentTerm, getNextTerm());
                    break;
                case MINUS_SIGN:
                    currentTerm = new CheckedSubtract(currentTerm, getNextTerm());
                    break;
                default:
                    return currentTerm;
            }
        }
    }


    public TripleExpression parse(String expression) throws IncorrectExpressionException, OverflowException {
        this.index = 0;
        this.number = 0;
        this.balance = 0;
        this.state = State.NONE;
        this.expression = expression;
        return parseTerms();
    }
}
