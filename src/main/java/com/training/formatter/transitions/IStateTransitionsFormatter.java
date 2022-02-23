package com.training.formatter.transitions;

import com.training.lexer.token.IToken;
import com.training.state.IState;

public interface IStateTransitionsFormatter {
    IState nextState(IState currentState, IToken token);
}
