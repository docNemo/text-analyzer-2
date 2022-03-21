package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.lexer.command.implementations.CreateTokenClosingMultilineComment;
import com.training.lexer.command.implementations.CreateTokenWhileWithNextChar;
import com.training.lexer.command.implementations.SetTokenCommon;
import com.training.lexer.command.implementations.CreateTokenLineComment;
import com.training.lexer.command.implementations.AddedToLexeme;
import com.training.lexer.command.implementations.SetTokenNewLine;
import com.training.lexer.command.implementations.CreateTokenClosingBrace;
import com.training.lexer.command.implementations.CreateTokenDoubleQuote;
import com.training.lexer.command.implementations.CreateTokenOpeningBrace;
import com.training.lexer.command.implementations.CreateTokenOpeningMultilineComment;
import com.training.lexer.command.implementations.CreateTokenSemicolon;
import com.training.lexer.command.implementations.SetTokenSlash;
import com.training.lexer.command.implementations.SetTokenSpace;
import com.training.lexer.command.implementations.CreateTokenCommonAddedAsteriskWithNextChar;
import com.training.lexer.command.implementations.CreateTokenCommonWithNextClosingMultilineComment;
import com.training.lexer.command.implementations.CreateTokenWithNextChar;
import com.training.lexer.command.implementations.Ignore;
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
        IState firstAsterisk = new State("FIRST_ASTERISK");
        IState asterisk = new State("ASTERISK");
        IState common = new State("COMMON");
        IState stateW = new State("w");
        IState stateH = new State("h");
        IState stateI = new State("i");
        IState stateL = new State("l");
        IState stateE = new State("e");

        //State - Start
        commands.put(new StatesPair<>(start, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(start, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(start, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(start, SLASH), new SetTokenSlash());
        commands.put(new StatesPair<>(start, ASTERISK), new SetTokenCommon());
        commands.put(new StatesPair<>(start, NEW_LINE), new SetTokenNewLine());
        commands.put(new StatesPair<>(start, SPACE), new SetTokenSpace());
        commands.put(new StatesPair<>(start, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair<>(start, 'w'), new SetTokenCommon());
        commands.put(new StatesPair<>(start, null), new SetTokenCommon());

        //State - W
        commands.put(new StatesPair<>(stateW, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(stateW, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(stateW, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(stateW, SLASH), new SetTokenSlash());
        commands.put(new StatesPair<>(stateW, ASTERISK), new SetTokenCommon());
        commands.put(new StatesPair<>(stateW, NEW_LINE), new SetTokenNewLine());
        commands.put(new StatesPair<>(stateW, SPACE), new SetTokenSpace());
        commands.put(new StatesPair<>(stateW, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair<>(stateW, 'h'), new AddedToLexeme());
        commands.put(new StatesPair<>(stateW, null), new AddedToLexeme());

        //State - H
        commands.put(new StatesPair<>(stateH, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(stateH, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(stateH, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(stateH, SLASH), new SetTokenSlash());
        commands.put(new StatesPair<>(stateH, ASTERISK), new SetTokenCommon());
        commands.put(new StatesPair<>(stateH, NEW_LINE), new SetTokenNewLine());
        commands.put(new StatesPair<>(stateH, SPACE), new SetTokenSpace());
        commands.put(new StatesPair<>(stateH, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair<>(stateH, 'i'), new AddedToLexeme());
        commands.put(new StatesPair<>(stateH, null), new AddedToLexeme());

        //State - I
        commands.put(new StatesPair<>(stateI, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(stateI, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(stateI, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(stateI, SLASH), new SetTokenSlash());
        commands.put(new StatesPair<>(stateI, ASTERISK), new SetTokenCommon());
        commands.put(new StatesPair<>(stateI, NEW_LINE), new SetTokenNewLine());
        commands.put(new StatesPair<>(stateI, SPACE), new SetTokenSpace());
        commands.put(new StatesPair<>(stateI, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair<>(stateI, 'l'), new AddedToLexeme());
        commands.put(new StatesPair<>(stateI, null), new AddedToLexeme());

        //State - L
        commands.put(new StatesPair<>(stateL, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair<>(stateL, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair<>(stateL, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair<>(stateL, SLASH), new SetTokenSlash());
        commands.put(new StatesPair<>(stateL, ASTERISK), new SetTokenCommon());
        commands.put(new StatesPair<>(stateL, NEW_LINE), new SetTokenNewLine());
        commands.put(new StatesPair<>(stateL, SPACE), new SetTokenSpace());
        commands.put(new StatesPair<>(stateL, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair<>(stateL, 'e'), new AddedToLexeme());
        commands.put(new StatesPair<>(stateL, null), new AddedToLexeme());

        //State - E
        commands.put(new StatesPair<>(stateE, OPENING_BRACE), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, CLOSING_BRACE), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, SEMICOLON), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, SLASH), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, ASTERISK), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, NEW_LINE), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, SPACE), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, DOUBLE_QUOTE), new CreateTokenWhileWithNextChar());
        commands.put(new StatesPair<>(stateE, null), new AddedToLexeme());

        //State - Slash
        commands.put(new StatesPair<>(slash, OPENING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, CLOSING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, SEMICOLON), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, SLASH), new CreateTokenLineComment());
        commands.put(new StatesPair<>(slash, ASTERISK), new CreateTokenOpeningMultilineComment());
        commands.put(new StatesPair<>(slash, NEW_LINE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, SPACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, DOUBLE_QUOTE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(slash, null), new CreateTokenWithNextChar());

        //State - Common
        commands.put(new StatesPair<>(common, OPENING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, CLOSING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, SEMICOLON), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, SLASH), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, ASTERISK), new Ignore());
        commands.put(new StatesPair<>(common, NEW_LINE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, SPACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, DOUBLE_QUOTE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(common, null), new AddedToLexeme());

        //State - newLine
        commands.put(new StatesPair<>(newLine, OPENING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, CLOSING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, SEMICOLON), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, SLASH), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, ASTERISK), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, NEW_LINE), new AddedToLexeme());
        commands.put(new StatesPair<>(newLine, SPACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, DOUBLE_QUOTE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(newLine, null), new CreateTokenWithNextChar());

        //State - Space
        commands.put(new StatesPair<>(space, OPENING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, CLOSING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, SEMICOLON), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, SLASH), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, ASTERISK), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, NEW_LINE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, SPACE), new AddedToLexeme());
        commands.put(new StatesPair<>(space, DOUBLE_QUOTE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(space, null), new CreateTokenWithNextChar());

        //State - First Asterisk
        commands.put(new StatesPair<>(firstAsterisk, OPENING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, CLOSING_BRACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, SEMICOLON), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, SLASH), new CreateTokenClosingMultilineComment());
        commands.put(new StatesPair<>(firstAsterisk, ASTERISK), new Ignore());
        commands.put(new StatesPair<>(firstAsterisk, NEW_LINE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, SPACE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, DOUBLE_QUOTE), new CreateTokenWithNextChar());
        commands.put(new StatesPair<>(firstAsterisk, null), new AddedToLexeme());

        //State - Asterisk
        commands.put(new StatesPair<>(asterisk, OPENING_BRACE), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, CLOSING_BRACE), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, SEMICOLON), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, SLASH), new CreateTokenCommonWithNextClosingMultilineComment());
        commands.put(new StatesPair<>(asterisk, ASTERISK), new AddedToLexeme());
        commands.put(new StatesPair<>(asterisk, NEW_LINE), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, SPACE), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, DOUBLE_QUOTE), new CreateTokenCommonAddedAsteriskWithNextChar());
        commands.put(new StatesPair<>(asterisk, null), new CreateTokenCommonAddedAsteriskWithNextChar());
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
