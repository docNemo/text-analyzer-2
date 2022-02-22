package com.training.lexer.state;

import java.util.Objects;

public class StatesPair {
    IState state;
    String typeChar;

    public  StatesPair(IState state, String typeChar) {
        this.state = state;
        this.typeChar = typeChar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatesPair that = (StatesPair) o;
        return Objects.equals(state, that.state) && Objects.equals(typeChar, that.typeChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, typeChar);
    }
}
