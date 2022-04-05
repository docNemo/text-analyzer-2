package com.training.lexer.transitions;

import com.training.configreader.ConfigReader;
import com.training.configreader.IConfigReader;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateTransitions implements IStateTransitions {
    private static final byte CURRENT_STATE = 0;
    private static final byte INPUT = 1;
    private static final byte NEXT_STATE = 2;

    private final Map<StatesPair<IState, String>, IState> stateTransitions;

    public StateTransitions(final String pathToConfig) {
        stateTransitions = new HashMap<>();

        IConfigReader configReader = new ConfigReader();
        List<String[]> actions = configReader.getListActionsState(pathToConfig, false);

        for (String[] action : actions) {
            String state = action[CURRENT_STATE];
            String input = action[INPUT];
            String nextStateName = action[NEXT_STATE];
            IState nextState;

            if (nextStateName != null) {
                nextState = new State(nextStateName);
            } else {
                nextState = null;
            }

            stateTransitions.put(new StatesPair<>(new State(state), input), nextState);
        }
    }

    @Override
    public IState nextState(IState currentState, char character) {
        IState nextState = stateTransitions.get(new StatesPair<>(currentState, String.valueOf(character)));

        if (nextState == null) {
            nextState = stateTransitions.get(new StatesPair<>(currentState, (String) null));
        }

        return nextState;
    }
}
