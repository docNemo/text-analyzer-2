package com.training.lexer.transitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.state.IState;
import com.training.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStateTransition {
    private IStateTransitions stateTransitions;
    private final String pathToLexerConfig = "src/main/java/com/training/lexer/lexer.yaml";

    @BeforeEach
    void setUp() {
        stateTransitions = new StateTransitions(pathToLexerConfig);
    }

    @Test
    void test1() {
        IState state = new State("start");
        char ch = '*';
        IState actual = stateTransitions.nextState(state, ch);
        assertEquals("asterisk", actual.getName());
    }
}
