package com.training.lexer;

import com.training.io.IReader;
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
    private IToken extraToken; //maybe after reading common lexeme
    private char nextChar;

    public Lexer(IReader reader) {
        this.reader = reader;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
        readedToken = readToken();
    }

    @Override
    public boolean hasNextToken() {
        return readedToken != null;
    }

    @Override
    public IToken getToken() {
        IToken returnableToken = readedToken;

        if (extraToken != null) {
            readedToken = extraToken;
            extraToken = null;
        } else {
            readedToken = readToken();
        }

        return returnableToken;
    }

    IToken readToken() {
        IState state = new State("START");

        ITokenBuilder tokenBuilder = new TokenBuilder();

        while(!state.getName().equals("TOKEN_READY") && reader.hasChar()) {
            char character;
            if (nextChar != 0) {
                character = nextChar;
                nextChar = 0;
            } else {
                character = reader.readChar();
            }

            ICommand command = commandRepository.getCommand(state, character);
            command.execute(character, tokenBuilder);
            state = stateTransitions.nextState(state, character);
        }
        if (tokenBuilder.getNextToken() != null) {
            extraToken = tokenBuilder.getNextToken();
        }
        if (tokenBuilder.getNextChar() != 0) {
            nextChar = tokenBuilder.getNextChar();
        }
        return tokenBuilder.getToken().getName() != null ? tokenBuilder.getToken() : null;
    }

}
