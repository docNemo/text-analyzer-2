package com.training.lexer.state.transitions;

import com.training.lexer.state.IState;

public interface IStateTransitions {
    IState nextState(IState currentState, char character);
}
