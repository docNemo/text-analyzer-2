package com.training.lexer.transitions;

import com.training.state.IState;

public interface IStateTransitions {
    IState nextState(IState currentState, char character);
}
