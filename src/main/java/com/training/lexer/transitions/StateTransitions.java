package com.training.lexer.transitions;

import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements IStateTransitions {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';

    Map<StatesPair<IState, Character>, IState> stateTransitions;

    public StateTransitions() {
        stateTransitions = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State("COMMON");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");

        //State - Start
        stateTransitions.put(new StatesPair<>(start, SLASH), slash);
        stateTransitions.put(new StatesPair<>(start, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair<>(start, NEW_LINE), newLine);
        stateTransitions.put(new StatesPair<>(start, SPACE), space);
        stateTransitions.put(new StatesPair<>(start, null), space);

        //State - Slash

        //State - Common
        stateTransitions.put(new StatesPair<>(common, SLASH), slash);
        stateTransitions.put(new StatesPair<>(common, ASTERISK), asterisk);

        //State - newLine
        stateTransitions.put(new StatesPair<>(newLine, NEW_LINE), newLine);

        //State - Space
        stateTransitions.put(new StatesPair<>(space, SPACE), space);

        //State - Asterisk
        stateTransitions.put(new StatesPair<>(asterisk, ASTERISK), asterisk);
    }

    @Override
    public IState nextState(IState currentState, char character) {
        IState nextState = stateTransitions.get(new StatesPair<>(currentState, character));

        if (nextState == null) {
            nextState = stateTransitions.get(new StatesPair<>(currentState, (Character) null));
        }

        return nextState;
    }
}
