package com.training.formatter.transitions;

import com.training.lexer.CharAnalyzer;
import com.training.lexer.token.IToken;
import com.training.lexer.transitions.IStateTransitions;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitionsFormatter implements IStateTransitionsFormatter {
    Map<StatesPair, IState> stateTransitions;

    public StateTransitionsFormatter() {
        stateTransitions = new HashMap<>();

        IState start = new State("START");
        IState code = new State("CODE");
        IState lineComment = new State("LINE_COMMENT");
        IState multilineComment = new State("MULTILINE_COMMENT");
        IState stringLiteral = new State("STRING_LITERAL");
        IState newLine = new State("NEW_LINE");

        //State - Start
        stateTransitions.put(new StatesPair(start, "OPENING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(start, "CLOSING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(start, "SEMICOLON"), newLine);
        stateTransitions.put(new StatesPair(start, "SLASH"), code);
        stateTransitions.put(new StatesPair(start, "NEW_LINE"), newLine);
        stateTransitions.put(new StatesPair(start, "SPACE"), newLine);
        stateTransitions.put(new StatesPair(start, "DOUBLE_QUOTE"), stringLiteral);
        stateTransitions.put(new StatesPair(start, "COMMON"), code);
        stateTransitions.put(new StatesPair(start, "LINE_COMMENT"), lineComment);
        stateTransitions.put(new StatesPair(start, "OPENING_MULTILINE_COMMENT"), multilineComment);
        stateTransitions.put(new StatesPair(start, "CLOSING_MULTILINE_COMMENT"), code);
        //State - Code
        stateTransitions.put(new StatesPair(code, "OPENING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(code, "CLOSING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(code, "SEMICOLON"), newLine);
        stateTransitions.put(new StatesPair(code, "SLASH"), code);
        stateTransitions.put(new StatesPair(code, "NEW_LINE"), code);
        stateTransitions.put(new StatesPair(code, "SPACE"), code);
        stateTransitions.put(new StatesPair(code, "DOUBLE_QUOTE"), stringLiteral);
        stateTransitions.put(new StatesPair(code, "COMMON"), code);
        stateTransitions.put(new StatesPair(code, "LINE_COMMENT"), lineComment);
        stateTransitions.put(new StatesPair(code, "OPENING_MULTILINE_COMMENT"), multilineComment);
        stateTransitions.put(new StatesPair(code, "CLOSING_MULTILINE_COMMENT"), code);

        //State - LineComment
        stateTransitions.put(new StatesPair(lineComment, "OPENING_BRACE"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "CLOSING_BRACE"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "SEMICOLON"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "SLASH"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "NEW_LINE"), code);
        stateTransitions.put(new StatesPair(lineComment, "SPACE"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "DOUBLE_QUOTE"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "COMMON"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "LINE_COMMENT"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "OPENING_MULTILINE_COMMENT"), lineComment);
        stateTransitions.put(new StatesPair(lineComment, "CLOSING_MULTILINE_COMMENT"), lineComment);

        //State - Multiline Comment
        stateTransitions.put(new StatesPair(multilineComment, "OPENING_BRACE"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "CLOSING_BRACE"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "SEMICOLON"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "SLASH"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "NEW_LINE"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "SPACE"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "DOUBLE_QUOTE"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "COMMON"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "LINE_COMMENT"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "OPENING_MULTILINE_COMMENT"), multilineComment);
        stateTransitions.put(new StatesPair(multilineComment, "CLOSING_MULTILINE_COMMENT"), code);

        //State - String Literal
        stateTransitions.put(new StatesPair(stringLiteral, "OPENING_BRACE"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "CLOSING_BRACE"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "SEMICOLON"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "SLASH"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "NEW_LINE"), code);
        stateTransitions.put(new StatesPair(stringLiteral, "SPACE"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "DOUBLE_QUOTE"), code);
        stateTransitions.put(new StatesPair(stringLiteral, "COMMON"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "LINE_COMMENT"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "OPENING_MULTILINE_COMMENT"), stringLiteral);
        stateTransitions.put(new StatesPair(stringLiteral, "CLOSING_MULTILINE_COMMENT"), stringLiteral);

        //State - New line
        stateTransitions.put(new StatesPair(newLine, "OPENING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(newLine, "CLOSING_BRACE"), newLine);
        stateTransitions.put(new StatesPair(newLine, "SEMICOLON"), newLine);
        stateTransitions.put(new StatesPair(newLine, "SLASH"), code);
        stateTransitions.put(new StatesPair(newLine, "NEW_LINE"), newLine);
        stateTransitions.put(new StatesPair(newLine, "SPACE"), newLine);
        stateTransitions.put(new StatesPair(newLine, "DOUBLE_QUOTE"), stringLiteral);
        stateTransitions.put(new StatesPair(newLine, "COMMON"), code);
        stateTransitions.put(new StatesPair(newLine, "LINE_COMMENT"), lineComment);
        stateTransitions.put(new StatesPair(newLine, "OPENING_MULTILINE_COMMENT"), multilineComment);
        stateTransitions.put(new StatesPair(newLine, "CLOSING_MULTILINE_COMMENT"), code);
    }

    @Override
    public IState nextState(IState currentState, IToken token) {
        return stateTransitions.get(new StatesPair(currentState, token.getName()));
    }
}
