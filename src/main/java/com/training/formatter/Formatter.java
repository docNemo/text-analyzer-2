package com.training.formatter;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.command.repository.CommandRepositoryFormatter;
import com.training.formatter.command.repository.ICommandRepositoryFormatter;
import com.training.formatter.transitions.IStateTransitionsFormatter;
import com.training.formatter.transitions.StateTransitionsFormatter;
import com.training.lexer.ILexer;
import com.training.lexer.token.IToken;
import com.training.exceptions.ReaderException;
import com.training.exceptions.WriteException;
import com.training.io.IWriter;
import com.training.state.IState;
import com.training.state.State;

public class Formatter implements IFormatter {

    private static final byte NUM_SPACES = 4;


    private final ILexer lexer;
    private final IWriter writer;
    private final ICommandRepositoryFormatter commandRepository;
    private final IStateTransitionsFormatter stateTransitions;


    public Formatter(ILexer lexer, IWriter writer) {
        this.lexer = lexer;
        this.writer = writer;
        commandRepository = new CommandRepositoryFormatter();
        stateTransitions = new StateTransitionsFormatter();
    }

    public void format() throws ReaderException, WriteException {
        int nestingLevel = 0;
        IState state = new State("START");
        while (lexer.hasNextToken()) {
            IToken token = lexer.getToken();
            if (token.getName().equals("CLOSING_BRACE")) {
                nestingLevel--;
            }
            ICommandFormatter command = commandRepository.getCommand(state, token);
            command.execute(token, getIndent(nestingLevel), writer);
            state = stateTransitions.nextState(state, token);
            if (token.getName().equals("OPENING_BRACE")) {
                nestingLevel++;
            }

        }
    }

    String getIndent(int nestingLevel) throws WriteException {
        int size_indent = NUM_SPACES * nestingLevel;
        return " ".repeat(size_indent);
    }
}
