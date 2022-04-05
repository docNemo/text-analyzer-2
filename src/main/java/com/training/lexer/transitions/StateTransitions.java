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

    private final Map<StatesPair<IState, String>, IState> stateTransitions;

    public StateTransitions(final String pathToConfig) {
        stateTransitions = new HashMap<>();

        IConfigReader configReader = new ConfigReader();
        List<String[]> actions = configReader.getListActionsState(pathToConfig, false);

        for (String[] action : actions) {
            String state = action[0];
            String input = action[1];
            String nextStateName = action[2];
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
