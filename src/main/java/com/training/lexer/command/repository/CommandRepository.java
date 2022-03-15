package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.lexer.command.implementations.CreateClosingMultilineComment;
import com.training.lexer.command.implementations.CreateFirstAsterisk;
import com.training.lexer.command.implementations.CreateLineComment;
import com.training.lexer.command.implementations.CreateMultiCharToken;
import com.training.lexer.command.implementations.CreateOpeningMultilineComment;
import com.training.lexer.command.implementations.CreateTokenClosingBrace;
import com.training.lexer.command.implementations.CreateTokenDoubleQuote;
import com.training.lexer.command.implementations.CreateTokenNewLine;
import com.training.lexer.command.implementations.CreateTokenOpeningBrace;
import com.training.lexer.command.implementations.CreateTokenSemicolon;
import com.training.lexer.command.implementations.CreateTokenSlash;
import com.training.lexer.command.implementations.CreateTokenSpace;
import com.training.lexer.command.implementations.CreateWithNextChar;
import com.training.lexer.command.implementations.CreateWithNextToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository implements ICommandRepository {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';
    private static final char DOUBLE_QUOTE = '\"';

    Map<StatesPair<IState, Character>, ICommand> commands;

    public CommandRepository() {
        commands = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");

        //State - Start
        commands.put(new StatesPair<>(start, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(start, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(start, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(start, SLASH), new CreateTokenSlash());
        commands.put(new StatesPair<>(start, ASTERISK), new CreateFirstAsterisk());
        commands.put(new StatesPair<>(start, NEW_LINE), new CreateTokenNewLine());
        commands.put(new StatesPair<>(start, SPACE), new CreateTokenSpace());
        commands.put(new StatesPair<>(start, DOUBLE_QUOTE), new CreateTokenDoubleQuote());

        //State - Slash
        commands.put(new StatesPair<>(slash, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(slash, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(slash, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair<>(slash, SLASH), new CreateLineComment());
        commands.put(new StatesPair<>(slash, ASTERISK), new CreateOpeningMultilineComment());
        commands.put(new StatesPair<>(slash, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair<>(slash, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair<>(slash, DOUBLE_QUOTE), new CreateWithNextToken());

        //State - newLine
        commands.put(new StatesPair<>(newLine, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(newLine, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(newLine, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair<>(newLine, SLASH), new CreateWithNextChar());
        commands.put(new StatesPair<>(newLine, ASTERISK), new CreateWithNextChar());
        commands.put(new StatesPair<>(newLine, NEW_LINE), new CreateMultiCharToken());
        commands.put(new StatesPair<>(newLine, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair<>(newLine, DOUBLE_QUOTE), new CreateWithNextToken());

        //State - Space
        commands.put(new StatesPair<>(space, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(space, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(space, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair<>(space, SLASH), new CreateWithNextChar());
        commands.put(new StatesPair<>(space, ASTERISK), new CreateWithNextChar());
        commands.put(new StatesPair<>(space, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair<>(space, SPACE), new CreateMultiCharToken());
        commands.put(new StatesPair<>(space, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair<>(space, null), new CreateWithNextChar());

        //State - Asterisk
        commands.put(new StatesPair<>(asterisk, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(asterisk, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair<>(asterisk, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair<>(asterisk, SLASH), new CreateClosingMultilineComment());
        commands.put(new StatesPair<>(asterisk, ASTERISK), new CreateMultiCharToken());
        commands.put(new StatesPair<>(asterisk, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair<>(asterisk, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair<>(asterisk, DOUBLE_QUOTE), new CreateWithNextToken());
    }

    @Override
    public ICommand getCommand(IState state, char character) {
        ICommand command = commands.get(new StatesPair<>(state, character));

        if (command == null) {
            command = commands.get(new StatesPair<>(state, (Character) null));
        }

        return command;
    }
}
