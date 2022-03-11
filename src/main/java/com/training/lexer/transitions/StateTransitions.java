package com.training.lexer.transitions;

import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements IStateTransitions {
    private static final String SPACE = " ";
    private static final String ASTERISK = "*";
    private static final String NEW_LINE = "\n";
    private static final String SLASH = "/";
    private static final String COMMON = "COMMON";

    Map<StatesPair, IState> stateTransitions;

    public StateTransitions() {
        stateTransitions = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State("COMMON");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");

        //State - Start
        stateTransitions.put(new StatesPair(start, SLASH), slash);
        stateTransitions.put(new StatesPair(start, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair(start, NEW_LINE), newLine);
        stateTransitions.put(new StatesPair(start, SPACE), space);
        stateTransitions.put(new StatesPair(start, COMMON), common);

        //State - Slash
        stateTransitions.put(new StatesPair(slash, COMMON), common);

        //State - Common
        stateTransitions.put(new StatesPair(common, SLASH), slash);
        stateTransitions.put(new StatesPair(common, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair(common, COMMON), common);

        //State - newLine
        stateTransitions.put(new StatesPair(newLine, NEW_LINE), newLine);

        //State - Space
        stateTransitions.put(new StatesPair(space, SPACE), space);

        //State - Asterisk
        stateTransitions.put(new StatesPair(asterisk, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair(asterisk, COMMON), common);
    }

    @Override
    public IState nextState(IState currentState, char character) {
        return stateTransitions.get(new StatesPair(currentState, typeOfChar(character)));
    }

    private String typeOfChar(char character) {
        if (
                character != ' '
                        && character != '*'
                        && character != '{'
                        && character != '}'
                        && character != '/'
                        && character != '"'
                        && character != ';'
                        && character != '\n'
        ) {
            return COMMON;
        } else {
            return String.valueOf(character);
        }
    }
}
