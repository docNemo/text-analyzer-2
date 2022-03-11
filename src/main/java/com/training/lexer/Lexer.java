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
    private IToken readedToken;
    private ITokenBuilder tokenBuilder;

    public Lexer(IReader reader) {
        this.reader = reader;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
        tokenBuilder = new TokenBuilder();
        readedToken = readToken();
    }

    @Override
    public boolean hasNextToken() {
        return readedToken != null;
    }

    @Override
    public IToken getToken() {
        IToken returnableToken = readedToken;
        readedToken = readToken();

        return returnableToken;
    }

    IToken readToken() {
        IState state = new State("START");
        tokenBuilder.newLexeme();

        IReader postponeReader = new StringReaderChar(tokenBuilder.getPostponeBuffer());

        while (state != null && postponeReader.hasChar()) {
            state = step(postponeReader, state, tokenBuilder);
        }

        tokenBuilder.setEmptyPostponeBuffer();

        while (state != null && reader.hasChar()) {
            state = step(reader, state, tokenBuilder);
        }

        return !tokenBuilder.getToken().getName().equals("") ? tokenBuilder.getToken() : null;
    }

    private IState step(IReader readerOnStep, IState state, ITokenBuilder builder) {
        char character = readerOnStep.readChar();

        ICommand command = commandRepository.getCommand(state, character);
        command.execute(character, builder);
        return stateTransitions.nextState(state, character);
    }

}
