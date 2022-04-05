package com.training.formatter.transitions;

import com.training.configreader.ConfigReader;
import com.training.configreader.IConfigReader;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateTransitionsFormatter implements IStateTransitionsFormatter {
    Map<StatesPair<IState, String>, IState> stateTransitions;

    public StateTransitionsFormatter(final String pathToConfig) {
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
    public IState nextState(IState currentState, IToken token) {
        IState state = stateTransitions.get(new StatesPair<>(currentState, token.getName()));

        if (state == null) {
            state = stateTransitions.get(new StatesPair<>(currentState, (String) null));
        }

        return state;
    }
}
