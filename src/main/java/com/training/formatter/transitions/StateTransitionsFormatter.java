package com.training.formatter.transitions;

import com.training.exceptions.CouldNotCreateStateTransitions;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateTransitionsFormatter implements IStateTransitionsFormatter {
    Map<StatesPair<IState, String>, IState> stateTransitions;

    public StateTransitionsFormatter(final String pathToConfig) {
        stateTransitions = new HashMap<>();

        try (InputStream file = new FileInputStream(pathToConfig)) {
            Yaml yaml = new Yaml();
            List statesDefs = yaml.load(file);

            for (Object stateDefObject : statesDefs) {
                Map stateDef = (Map) stateDefObject;
                String state = stateDef.get("state").toString();
                List actionsDefs = (List) stateDef.get("actions");

                for (Object actionDefObject : actionsDefs) {
                    Map actionDef = (Map) actionDefObject;
                    String stateName = (String) actionDef.get("state");
                    String input = (String) actionDef.get("input");
                    IState nextState;
                    if (stateName != null) {
                        nextState = new State(stateName);
                    } else {
                        nextState = null;
                    }
                    stateTransitions.put(new StatesPair<>(new State(state), input), nextState);
                }

            }

        } catch (FileNotFoundException e) {
            throw new CouldNotCreateStateTransitions("Not found file: " + pathToConfig, e);
        } catch (IOException e) {
            throw new CouldNotCreateStateTransitions("Could  not read file: " + pathToConfig, e);
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
