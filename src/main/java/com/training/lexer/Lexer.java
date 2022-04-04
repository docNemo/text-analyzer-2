package com.training.lexer;

import com.training.io.IReader;
import com.training.io.string.StringReaderChar;
import com.training.lexer.command.ICommand;
import com.training.lexer.command.repository.CommandRepository;
import com.training.lexer.command.repository.ICommandRepository;
import com.training.state.IState;
import com.training.state.State;
import com.training.lexer.transitions.IStateTransitions;
import com.training.lexer.transitions.StateTransitions;
import com.training.lexer.token.IToken;
import com.training.lexer.tokenbuilder.ITokenBuilder;
import com.training.lexer.tokenbuilder.TokenBuilder;

public class Lexer implements ILexer {

    private final IReader reader;
    private final ICommandRepository commandRepository;
    private final IStateTransitions stateTransitions;
    private final ITokenBuilder tokenBuilder;
    private final String pathToLexerConfig = "src/main/java/com/training/lexer/lexer.yaml";

    public Lexer(IReader reader, String pathToConfig) {
        this.reader = reader;
        commandRepository = new CommandRepository(pathToConfig);
        stateTransitions = new StateTransitions(pathToConfig);
        tokenBuilder = new TokenBuilder();
    }

    @Override
    public boolean hasNextToken() {
        return tokenBuilder.getPostponeBuffer().length() > 0 || reader.hasChar();
    }

    @Override
    public IToken getToken() {
        IState state = new State("start");
        tokenBuilder.newLexeme();

        IReader postponeReader = new StringReaderChar(tokenBuilder.getPostponeBuffer().toString());

        while (postponeReader.hasChar() && state != null) {
            state = step(postponeReader, state, tokenBuilder);
        }

        tokenBuilder.setEmptyPostponeBuffer();

        while (reader.hasChar() && state != null) {
            state = step(reader, state, tokenBuilder);
        }

        return tokenBuilder.getToken();
    }

    private IState step(IReader readerOnStep, IState state, ITokenBuilder builder) {
        char character = readerOnStep.readChar();

        ICommand command = commandRepository.getCommand(state, character);
        command.execute(character, builder);
        return stateTransitions.nextState(state, character);
    }

}
