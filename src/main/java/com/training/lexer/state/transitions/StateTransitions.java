package com.training.lexer.state.transitions;

import com.training.lexer.state.IState;
import com.training.lexer.state.State;
import com.training.lexer.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements IStateTransitions {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';
    private static final char DOUBLE_QUOTE = '"';

    Map<StatesPair, IState> stateTransitions;

    public StateTransitions() {
        stateTransitions = new HashMap<>();

        IState init = new State("INIT");
        IState common = new State("COMMON");
        IState firstSlash = new State("FIRST_SLASH");
        IState lineComment = new State("LINE_COMMENT");
        IState multilineComment = new State("MULTILINE_COMMENT");
        IState asterisk = new State("ASTERISK");
        IState strLiteral = new State("STRING_LITERAL");

        //State - Init
        stateTransitions.put(new StatesPair(init, analyse(OPENING_BRACE)), common);
        stateTransitions.put(new StatesPair(init, analyse(CLOSING_BRACE)), common);
        stateTransitions.put(new StatesPair(init, analyse(SEMICOLON)), common);
        stateTransitions.put(new StatesPair(init, analyse(SLASH)), firstSlash);
        stateTransitions.put(new StatesPair(init, analyse(ASTERISK)), common);
        stateTransitions.put(new StatesPair(init, analyse(NEW_LINE)), common);
        stateTransitions.put(new StatesPair(init, analyse(SPACE)), common);
        stateTransitions.put(new StatesPair(init, analyse(DOUBLE_QUOTE)), strLiteral);
        stateTransitions.put(new StatesPair(init, "COMMON_CHAR"), common);

        //State - Common
        stateTransitions.put(new StatesPair(common, analyse(OPENING_BRACE)), common);
        stateTransitions.put(new StatesPair(common, analyse(CLOSING_BRACE)), common);
        stateTransitions.put(new StatesPair(common, analyse(SEMICOLON)), common);
        stateTransitions.put(new StatesPair(common, analyse(SLASH)), firstSlash);
        stateTransitions.put(new StatesPair(common, analyse(ASTERISK)), common);
        stateTransitions.put(new StatesPair(common, analyse(NEW_LINE)), common);
        stateTransitions.put(new StatesPair(common, analyse(SPACE)), common);
        stateTransitions.put(new StatesPair(common, analyse(DOUBLE_QUOTE)), strLiteral);
        stateTransitions.put(new StatesPair(common, "COMMON_CHAR"), common);

        //State - FirstSlash
        stateTransitions.put(new StatesPair(firstSlash, analyse(OPENING_BRACE)), common);
        stateTransitions.put(new StatesPair(firstSlash, analyse(CLOSING_BRACE)), common);
        stateTransitions.put(new StatesPair(firstSlash, analyse(SEMICOLON)), common);
        stateTransitions.put(new StatesPair(firstSlash, analyse(SLASH)), lineComment);
        stateTransitions.put(new StatesPair(firstSlash, analyse(ASTERISK)), multilineComment);
        stateTransitions.put(new StatesPair(firstSlash, analyse(NEW_LINE)), common);
        stateTransitions.put(new StatesPair(firstSlash, analyse(SPACE)), common);
        stateTransitions.put(new StatesPair(firstSlash, analyse(DOUBLE_QUOTE)), strLiteral);
        stateTransitions.put(new StatesPair(firstSlash, "COMMON_CHAR"), common);

        //State - LineComment
        stateTransitions.put(new StatesPair(lineComment, analyse(OPENING_BRACE)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(CLOSING_BRACE)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(SEMICOLON)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(SLASH)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(ASTERISK)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(NEW_LINE)), common);
        stateTransitions.put(new StatesPair(lineComment, analyse(SPACE)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, analyse(DOUBLE_QUOTE)), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "COMMON_CHAR"), lineComment);

        //State - MultilineComment
        stateTransitions.put(new StatesPair(multilineComment, analyse(OPENING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(CLOSING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(SEMICOLON)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(SLASH)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(ASTERISK)), asterisk);
        stateTransitions.put(new StatesPair(multilineComment, analyse(NEW_LINE)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(SPACE)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, analyse(DOUBLE_QUOTE)), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "COMMON_CHAR"), multilineComment);

        //State - Asterisk
        stateTransitions.put(new StatesPair(asterisk, analyse(OPENING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(CLOSING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(SEMICOLON)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(SLASH)), common);
        stateTransitions.put(new StatesPair(asterisk, analyse(ASTERISK)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(NEW_LINE)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(SPACE)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, analyse(DOUBLE_QUOTE)), multilineComment);
        stateTransitions.put(new StatesPair(asterisk, "COMMON_CHAR"), multilineComment);

        //State - StrLiteral
        stateTransitions.put(new StatesPair(strLiteral, analyse(OPENING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(strLiteral, analyse(CLOSING_BRACE)), multilineComment);
        stateTransitions.put(new StatesPair(strLiteral, analyse(SEMICOLON)), multilineComment);
        stateTransitions.put(new StatesPair(strLiteral, analyse(SLASH)), common);
        stateTransitions.put(new StatesPair(strLiteral, analyse(ASTERISK)), multilineComment);
        stateTransitions.put(new StatesPair(strLiteral, analyse(NEW_LINE)), common);
        stateTransitions.put(new StatesPair(strLiteral, analyse(SPACE)), multilineComment);
        stateTransitions.put(new StatesPair(strLiteral, analyse(DOUBLE_QUOTE)), common);
        stateTransitions.put(new StatesPair(strLiteral, "COMMON_CHAR"), multilineComment);
    }

    @Override
    public IState nextState(IState currentState, char character) {
        return stateTransitions.get(new StatesPair(currentState, analyse(character)));
    }

    String analyse(char character) {
        return switch (character) {
            case NEW_LINE -> "NEW_LINE";
            case SPACE -> "SPACE";
            case OPENING_BRACE -> "OPENING_BRACE";
            case CLOSING_BRACE -> "CLOSING_BRACE";
            case SEMICOLON -> "SEMICOLON";
            case ASTERISK -> "ASTERISK";
            case SLASH -> "SLASH";
            case DOUBLE_QUOTE -> "DOUBLE_QUOTE";
            default -> "COMMON_CHAR";
        };
    }
}
