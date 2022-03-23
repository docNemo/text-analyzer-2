package com.training.lexer.transitions;

import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements IStateTransitions {

    private final Map<StatesPair<IState, Character>, IState> stateTransitions;

    public StateTransitions() {
        stateTransitions = new HashMap<>();

        //Start
        stateTransitions.put(new StatesPair<>(new State("start"), null), null);
        stateTransitions.put(new StatesPair<>(new State("start"), ';'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), '\n'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), '('), null);
        stateTransitions.put(new StatesPair<>(new State("start"), ')'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), '}'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), '{'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), ' '), new State("spacing"));
        stateTransitions.put(new StatesPair<>(new State("start"), '/'), new State("slash"));
        stateTransitions.put(new StatesPair<>(new State("start"), '*'), new State("asterisk"));
        stateTransitions.put(new StatesPair<>(new State("start"), '"'), null);
        stateTransitions.put(new StatesPair<>(new State("start"), 'f'), new State("forF"));

        //for f
        stateTransitions.put(new StatesPair<>(new State("forF"), null), null);
        stateTransitions.put(new StatesPair<>(new State("forF"), 'o'), new State("forO"));

        //for o
        stateTransitions.put(new StatesPair<>(new State("forO"), null), null);
        stateTransitions.put(new StatesPair<>(new State("forO"), 'r'), null);

        //spacing
        stateTransitions.put(new StatesPair<>(new State("spacing"), null), null);
        stateTransitions.put(new StatesPair<>(new State("spacing"), ' '), new State("spacing"));

        //slash
        stateTransitions.put(new StatesPair<>(new State("slash"), null), null);
        stateTransitions.put(new StatesPair<>(new State("slash"), '*'), null);
        stateTransitions.put(new StatesPair<>(new State("slash"), '/'), null);

        //asterisk
        stateTransitions.put(new StatesPair<>(new State("asterisk"), null), null);
        stateTransitions.put(new StatesPair<>(new State("asterisk"), '/'), null);

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
