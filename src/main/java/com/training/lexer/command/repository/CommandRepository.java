package com.training.lexer.command.repository;

import com.training.lexer.CharAnalyzer;
import com.training.lexer.command.ICommand;
import com.training.lexer.command.implementations.CreateClosingMultilineComment;
import com.training.lexer.command.implementations.CreateFirstAsterisk;
import com.training.lexer.command.implementations.CreateLineComment;
import com.training.lexer.command.implementations.CreateMultiCharToken;
import com.training.lexer.command.implementations.CreateOpeningMultilineComment;
import com.training.lexer.command.implementations.CreateToken;
import com.training.lexer.command.implementations.CreateWithNextChar;
import com.training.lexer.command.implementations.CreateWithNextToken;
import com.training.lexer.state.IState;
import com.training.lexer.state.State;
import com.training.lexer.state.StatesPair;

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
    private static final char DOUBLE_QUOTE = '"';

    Map<StatesPair, ICommand> commands;

    public CommandRepository() {
        commands = new HashMap<>();

        IState start = new State("START");
        IState slash = new State("SLASH");
        IState common = new State("COMMON");
        IState newLine = new State("NEW_LINE");
        IState space = new State("SPACE");
        IState asterisk = new State("ASTERISK");
        IState ready = new State("TOKEN_READY");

        //State - Start
        commands.put(new StatesPair(start, CharAnalyzer.analyse(OPENING_BRACE)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(SEMICOLON)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(SLASH)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(ASTERISK)), new CreateFirstAsterisk());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(NEW_LINE)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(SPACE)), new CreateToken());
        commands.put(new StatesPair(start, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateToken());
        commands.put(new StatesPair(start, "COMMON"), new CreateToken());

        //State - Slash
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(OPENING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(SEMICOLON)), new CreateWithNextToken());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(SLASH)), new CreateLineComment());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(ASTERISK)), new CreateOpeningMultilineComment());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(NEW_LINE)), new CreateWithNextChar());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(SPACE)), new CreateWithNextChar());
        commands.put(new StatesPair(slash, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateWithNextToken());
        commands.put(new StatesPair(slash, "COMMON"), new CreateMultiCharToken());

        //State - Common
        commands.put(new StatesPair(common, CharAnalyzer.analyse(OPENING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(SEMICOLON)), new CreateWithNextToken());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(SLASH)), new CreateWithNextChar());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(ASTERISK)), new CreateMultiCharToken());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(NEW_LINE)), new CreateWithNextChar());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(SPACE)), new CreateWithNextChar());
        commands.put(new StatesPair(common, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateWithNextToken());
        commands.put(new StatesPair(common, "COMMON"), new CreateMultiCharToken());

        //State - newLine
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(OPENING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(SEMICOLON)), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(SLASH)), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(ASTERISK)), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(NEW_LINE)), new CreateMultiCharToken());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(SPACE)), new CreateWithNextChar());
        commands.put(new StatesPair(newLine, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateWithNextToken());
        commands.put(new StatesPair(newLine, "COMMON"), new CreateWithNextChar());

        //State - Space
        commands.put(new StatesPair(space, CharAnalyzer.analyse(OPENING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(SEMICOLON)), new CreateWithNextToken());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(SLASH)), new CreateWithNextChar());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(ASTERISK)), new CreateWithNextChar());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(NEW_LINE)), new CreateWithNextChar());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(SPACE)), new CreateMultiCharToken());
        commands.put(new StatesPair(space, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateWithNextToken());
        commands.put(new StatesPair(space, "COMMON"), new CreateWithNextChar());

        //State - Asterisk
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(OPENING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(CLOSING_BRACE)), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(SEMICOLON)), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(SLASH)), new CreateClosingMultilineComment());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(ASTERISK)), new CreateMultiCharToken());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(NEW_LINE)), new CreateWithNextChar());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(SPACE)), new CreateWithNextChar());
        commands.put(new StatesPair(asterisk, CharAnalyzer.analyse(DOUBLE_QUOTE)), new CreateWithNextToken());
        commands.put(new StatesPair(asterisk, "COMMON"), new CreateMultiCharToken());
    }

    @Override
    public ICommand getCommand(IState state, char character) {
        return commands.get(new StatesPair(state, CharAnalyzer.analyse(character)));
    }
}
