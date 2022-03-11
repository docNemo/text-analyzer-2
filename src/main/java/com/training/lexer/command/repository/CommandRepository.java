package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.lexer.command.implementations.CreateClosingMultilineComment;
import com.training.lexer.command.implementations.CreateFirstAsterisk;
import com.training.lexer.command.implementations.CreateLineComment;
import com.training.lexer.command.implementations.CreateMultiCharToken;
import com.training.lexer.command.implementations.CreateOpeningMultilineComment;
import com.training.lexer.command.implementations.CreateTokenClosingBrace;
import com.training.lexer.command.implementations.CreateTokenCommon;
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
    private static final String SPACE = " ";
    private static final String ASTERISK = "*";
    private static final String OPENING_BRACE = "{";
    private static final String CLOSING_BRACE = "}";
    private static final String SEMICOLON = ";";
    private static final String NEW_LINE = "\n";
    private static final String SLASH = "/";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String COMMON = "COMMON";

    Map<StatesPair, ICommand> commands;

    public CommandRepository() {
        commands = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State(COMMON);
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");

        //State - Start
        commands.put(new StatesPair(start, OPENING_BRACE), new CreateTokenOpeningBrace());
        commands.put(new StatesPair(start, CLOSING_BRACE), new CreateTokenClosingBrace());
        commands.put(new StatesPair(start, SEMICOLON), new CreateTokenSemicolon());
        commands.put(new StatesPair(start, SLASH), new CreateTokenSlash());
        commands.put(new StatesPair(start, ASTERISK), new CreateFirstAsterisk());
        commands.put(new StatesPair(start, NEW_LINE), new CreateTokenNewLine());
        commands.put(new StatesPair(start, SPACE), new CreateTokenSpace());
        commands.put(new StatesPair(start, DOUBLE_QUOTE), new CreateTokenDoubleQuote());
        commands.put(new StatesPair(start, COMMON), new CreateTokenCommon());

        //State - Slash
        commands.put(new StatesPair(slash, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(slash, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(slash, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair(slash, SLASH), new CreateLineComment());
        commands.put(new StatesPair(slash, ASTERISK), new CreateOpeningMultilineComment());
        commands.put(new StatesPair(slash, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair(slash, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair(slash, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair(slash, COMMON), new CreateMultiCharToken());

        //State - Common
        commands.put(new StatesPair(common, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(common, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(common, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair(common, SLASH), new CreateWithNextChar());
        commands.put(new StatesPair(common, ASTERISK), new CreateMultiCharToken());
        commands.put(new StatesPair(common, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair(common, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair(common, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair(common, COMMON), new CreateMultiCharToken());

        //State - newLine
        commands.put(new StatesPair(newLine, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, SLASH), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, ASTERISK), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, NEW_LINE), new CreateMultiCharToken());
        commands.put(new StatesPair(newLine, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, COMMON), new CreateWithNextChar());

        //State - Space
        commands.put(new StatesPair(space, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(space, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(space, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair(space, SLASH), new CreateWithNextChar());
        commands.put(new StatesPair(space, ASTERISK), new CreateWithNextChar());
        commands.put(new StatesPair(space, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair(space, SPACE), new CreateMultiCharToken());
        commands.put(new StatesPair(space, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair(space, COMMON), new CreateWithNextChar());

        //State - Asterisk
        commands.put(new StatesPair(asterisk, OPENING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, CLOSING_BRACE), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, SEMICOLON), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, SLASH), new CreateClosingMultilineComment());
        commands.put(new StatesPair(asterisk, ASTERISK), new CreateMultiCharToken());
        commands.put(new StatesPair(asterisk, NEW_LINE), new CreateWithNextChar());
        commands.put(new StatesPair(asterisk, SPACE), new CreateWithNextChar());
        commands.put(new StatesPair(asterisk, DOUBLE_QUOTE), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, COMMON), new CreateMultiCharToken());
    }

    @Override
    public ICommand getCommand(IState state, char character) {
        return commands.get(new StatesPair(state, typeOfChar(character)));
    }
    
    private String typeOfChar(char character) {
        if (
                character != ' '
                && character != '*'
                && character != '{'
                && character != '}'
                && character != '/'
                && character != '"'
                && character != ';'
                && character != '\n'
        ) {
            return COMMON;
        } else {
            return String.valueOf(character);
        }
    }
}
