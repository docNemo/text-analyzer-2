package com.training.formatter;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.command.repository.CommandRepositoryFormatter;
import com.training.formatter.command.repository.ICommandRepositoryFormatter;
import com.training.formatter.context.ContextFormatter;
import com.training.formatter.context.IContextFormatter;
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
    private final ICommandRepositoryFormatter commandRepository;
    private final IStateTransitionsFormatter stateTransitions;

    public Formatter(final String pathToConfig) {
        commandRepository = new CommandRepositoryFormatter(pathToConfig);
        stateTransitions = new StateTransitionsFormatter(pathToConfig);
    }

    public void format(ILexer lexer, IWriter writer) throws ReaderException, WriteException {
        IContextFormatter context = new ContextFormatter(writer);

        IState state = new State("start");
        while (lexer.hasNextToken() && state != null) {
            IToken token = lexer.getToken();
            ICommandFormatter command = commandRepository.getCommand(state, token);
            command.execute(token, context);
            state = stateTransitions.nextState(state, token);
        }
    }
}
