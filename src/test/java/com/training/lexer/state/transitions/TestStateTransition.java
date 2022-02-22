package com.training.lexer.state.transitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.lexer.state.IState;
import com.training.lexer.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStateTransition {
    IStateTransitions stateTransitions;

    @BeforeEach
    void setUp() {
        stateTransitions = new StateTransitions();
    }

    @Test
    void test1() {
        IState state = new State("START");
        char ch = '*';
        IState actual = stateTransitions.nextState(state, ch);
        assertEquals("ASTERISK", actual.getName());
    }

    @Test
    void test2() {

    }
}
