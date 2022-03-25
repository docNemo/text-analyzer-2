package com.training.formatter.transitions;

import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitionsFormatter implements IStateTransitionsFormatter {
    Map<StatesPair<IState, String>, IState> stateTransitions;

    public StateTransitionsFormatter() {
        stateTransitions = new HashMap<>();

        // start
        createTransitionsStart();

        // char
        createTransitionsChar();

        // openbracket
        createTransitionsOpenBracet();

        // space
        createTransitionsSpace();

        // new line
        createTransitionsNewLine();

        // true new line
        createTransitionsTrueNewLine();

        // second new line
        createTransitionsSecondNewLine();

        // for
        stateTransitions.put(new StatesPair<>(new State("for"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("for"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("for"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("for"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("for"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("for"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("for"), "newline"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("for"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("for"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("for"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("for"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("for"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("for"), "space"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("for"), "spaces"), new State("space"));

        // one line comment
        stateTransitions.put(new StatesPair<>(new State("onelinecomment"), null), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("onelinecomment"), "newline"), new State("truenewline"));

        // multiline comment
        stateTransitions.put(new StatesPair<>(new State("multilinecomment"), null), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("multilinecomment"), "closemultilinecomment"), new State("newline"));

        // quotemark
        stateTransitions.put(new StatesPair<>(new State("quotemark"), null), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("quotemark"), "quotemark"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("quotemark"), "newline"), new State("truenewline"));
    }

    private void createTransitionsStart() {
        stateTransitions.put(new StatesPair<>(new State("start"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("start"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("start"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("start"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("start"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("start"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("start"), "newline"), new State("truenewline"));
        stateTransitions.put(new StatesPair<>(new State("start"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("start"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("start"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("start"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("start"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("start"), "space"), new State("start"));
        stateTransitions.put(new StatesPair<>(new State("start"), "spaces"), new State("start"));
    }

    private void createTransitionsChar() {
        stateTransitions.put(new StatesPair<>(new State("char"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("char"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("char"), "closebracket"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("char"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("char"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("char"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("char"), "newline"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("char"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("char"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("char"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("char"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("char"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("char"), "space"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("char"), "spaces"), new State("space"));
    }

    private void createTransitionsOpenBracet() {
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "newline"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "space"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("openbracket"), "spaces"), new State("openbracket"));
    }

    private void createTransitionsSpace() {
        stateTransitions.put(new StatesPair<>(new State("space"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("space"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("space"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("space"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("space"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("space"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("space"), "newline"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("space"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("space"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("space"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("space"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("space"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("space"), "space"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("space"), "spaces"), new State("space"));
    }

    private void createTransitionsNewLine() {
        stateTransitions.put(new StatesPair<>(new State("newline"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "newline"), new State("truenewline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "space"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("newline"), "spaces"), new State("newline"));
    }

    private void createTransitionsTrueNewLine() {
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "openbracket"), new State("openbracket"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "closebracket"), new State("space"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "newline"), new State("secondnewline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "space"), new State("truenewline"));
        stateTransitions.put(new StatesPair<>(new State("truenewline"), "spaces"), new State("truenewline"));
    }

    private void createTransitionsSecondNewLine() {
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "char"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "openbracket"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "closebracket"), new State("char"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "openbrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "closebrace"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "closemultilinecomment"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "newline"), new State("secondnewline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "semicolon"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "for"), new State("for"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "onelinecomment"), new State("onelinecomment"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "openmultilinecomment"), new State("multilinecomment"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "quotemark"), new State("quotemark"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "space"), new State("newline"));
        stateTransitions.put(new StatesPair<>(new State("secondnewline"), "spaces"), new State("newline"));
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
