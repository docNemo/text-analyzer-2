package com.training.state;

import java.util.Objects;

public class StatesPair {
    IState state;
    String type;

    public  StatesPair(IState state, String type) {
        this.state = state;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatesPair that = (StatesPair) o;
        return Objects.equals(state, that.state) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, type);
    }
}
