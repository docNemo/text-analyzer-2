package com.training.lexer.transitions;

import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements IStateTransitions {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';
    private static final char DOUBLE_QUOTE = '\"';

    Map<StatesPair<IState, Character>, IState> stateTransitions;

    public StateTransitions() {
        stateTransitions = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State("COMMON");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState firstAsterisk = new State("FIRST_ASTERISK");
        IState asterisk = new State("ASTERISK");
        IState ready = new State("READY");
        IState stateW = new State("w");
        IState stateH = new State("h");
        IState stateI = new State("i");
        IState stateL = new State("l");
        IState stateE = new State("e");

        //State - Start
        stateTransitions.put(new StatesPair<>(start, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(start, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(start, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(start, SLASH), slash);
        stateTransitions.put(new StatesPair<>(start, ASTERISK), firstAsterisk);
        stateTransitions.put(new StatesPair<>(start, NEW_LINE), newLine);
        stateTransitions.put(new StatesPair<>(start, SPACE), space);
        stateTransitions.put(new StatesPair<>(start, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(start, 'w'), stateW);
        stateTransitions.put(new StatesPair<>(start, null), common);

        //State - W
        stateTransitions.put(new StatesPair<>(stateW, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateW, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateW, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(stateW, SLASH), ready);
        stateTransitions.put(new StatesPair<>(stateW, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(stateW, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(stateW, SPACE), ready);
        stateTransitions.put(new StatesPair<>(stateW, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(stateW, 'h'), stateH);
        stateTransitions.put(new StatesPair<>(stateW, null), common);

        //State - H
        stateTransitions.put(new StatesPair<>(stateH, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateH, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateH, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(stateH, SLASH), ready);
        stateTransitions.put(new StatesPair<>(stateH, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(stateH, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(stateH, SPACE), ready);
        stateTransitions.put(new StatesPair<>(stateH, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(stateH, 'i'), stateI);
        stateTransitions.put(new StatesPair<>(stateH, null), common);

        //State - I
        stateTransitions.put(new StatesPair<>(stateI, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateI, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateI, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(stateI, SLASH), ready);
        stateTransitions.put(new StatesPair<>(stateI, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(stateI, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(stateI, SPACE), ready);
        stateTransitions.put(new StatesPair<>(stateI, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(stateI, 'l'), stateL);
        stateTransitions.put(new StatesPair<>(stateI, null), common);

        //State - L
        stateTransitions.put(new StatesPair<>(stateL, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateL, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateL, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(stateL, SLASH), ready);
        stateTransitions.put(new StatesPair<>(stateL, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(stateL, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(stateL, SPACE), ready);
        stateTransitions.put(new StatesPair<>(stateL, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(stateL, 'e'), stateE);
        stateTransitions.put(new StatesPair<>(stateL, null), common);

        //State - E
        stateTransitions.put(new StatesPair<>(stateE, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateE, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(stateE, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(stateE, SLASH), ready);
        stateTransitions.put(new StatesPair<>(stateE, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(stateE, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(stateE, SPACE), ready);
        stateTransitions.put(new StatesPair<>(stateE, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(stateE, null), common);

        //State - Slash
        stateTransitions.put(new StatesPair<>(slash, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(slash, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(slash, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(slash, SLASH), ready);
        stateTransitions.put(new StatesPair<>(slash, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(slash, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(slash, SPACE), ready);
        stateTransitions.put(new StatesPair<>(slash, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(slash, null), ready);

        //State - Common
        stateTransitions.put(new StatesPair<>(common, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(common, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(common, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(common, SLASH), slash);
        stateTransitions.put(new StatesPair<>(common, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair<>(common, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(common, SPACE), ready);
        stateTransitions.put(new StatesPair<>(common, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(common, null), common);

        //State - newLine
        stateTransitions.put(new StatesPair<>(newLine, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(newLine, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(newLine, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(newLine, SLASH), ready);
        stateTransitions.put(new StatesPair<>(newLine, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(newLine, NEW_LINE), newLine);
        stateTransitions.put(new StatesPair<>(newLine, SPACE), ready);
        stateTransitions.put(new StatesPair<>(newLine, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(newLine, null), ready);

        //State - Space
        stateTransitions.put(new StatesPair<>(space, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(space, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(space, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(space, SLASH), ready);
        stateTransitions.put(new StatesPair<>(space, ASTERISK), ready);
        stateTransitions.put(new StatesPair<>(space, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(space, SPACE), space);
        stateTransitions.put(new StatesPair<>(space, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(space, null), ready);

        //State - First Asterisk
        stateTransitions.put(new StatesPair<>(firstAsterisk, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, SLASH), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair<>(firstAsterisk, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, SPACE), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(firstAsterisk, null), common);

        //State - Asterisk
        stateTransitions.put(new StatesPair<>(asterisk, OPENING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(asterisk, CLOSING_BRACE), ready);
        stateTransitions.put(new StatesPair<>(asterisk, SEMICOLON), ready);
        stateTransitions.put(new StatesPair<>(asterisk, SLASH), ready);
        stateTransitions.put(new StatesPair<>(asterisk, ASTERISK), asterisk);
        stateTransitions.put(new StatesPair<>(asterisk, NEW_LINE), ready);
        stateTransitions.put(new StatesPair<>(asterisk, SPACE), ready);
        stateTransitions.put(new StatesPair<>(asterisk, DOUBLE_QUOTE), ready);
        stateTransitions.put(new StatesPair<>(asterisk, null), common);
    }

    @Override
    public IState nextState(IState currentState, char character) {
        IState nextState = stateTransitions.get(new StatesPair<>(currentState, character));

        if (nextState == null) {
            nextState = stateTransitions.get(new StatesPair<>(currentState, (Character) null));
        }
        if (nextState.getName().equals("READY")) {
            nextState = null;
        }

        return nextState;
    }
}
