package com.training.lexer.transitions;

import com.training.lexer.CharAnalyzer;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

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

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State("COMMON");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");
        IState ready = new State("TOKEN_READY");

        //State - Start
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(SLASH)), slash);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(ASTERISK)), asterisk);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(NEW_LINE)), newLine);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(SPACE)), space);
        stateTransitions.put(new StatesPair(start, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(start, "COMMON"), common);

        //State - Slash
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(SLASH)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(ASTERISK)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(NEW_LINE)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(SPACE)), ready);
        stateTransitions.put(new StatesPair(slash, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(slash, "COMMON"), common);

        //State - Common
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(SLASH)), slash);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(ASTERISK)), asterisk);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(NEW_LINE)), ready);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(SPACE)), ready);
        stateTransitions.put(new StatesPair(common, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(common, "COMMON"), common);

        //State - newLine
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(SLASH)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(ASTERISK)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(NEW_LINE)), newLine);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(SPACE)), ready);
        stateTransitions.put(new StatesPair(newLine, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(newLine, "COMMON"), ready);

        //State - Space
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(SLASH)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(ASTERISK)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(NEW_LINE)), ready);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(SPACE)), space);
        stateTransitions.put(new StatesPair(space, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(space, "COMMON"), ready);

        //State - Asterisk
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(OPENING_BRACE)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(CLOSING_BRACE)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(SEMICOLON)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(SLASH)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(ASTERISK)), asterisk);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(NEW_LINE)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(SPACE)), ready);
        stateTransitions.put(new StatesPair(asterisk, CharAnalyzer.analyse(DOUBLE_QUOTE)), ready);
        stateTransitions.put(new StatesPair(asterisk, "COMMON"), common);
    }

    @Override
    public IState nextState(IState currentState, char character) {
        return stateTransitions.get(new StatesPair(currentState, CharAnalyzer.analyse(character)));
    }
}
