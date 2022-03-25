package com.training.formatter.command.repository;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.command.implementations.CloseBrace;
import com.training.formatter.command.implementations.CloseBraceOnNewLine;
import com.training.formatter.command.implementations.Lexeme;
import com.training.formatter.command.implementations.LexemeOnNewLine;
import com.training.formatter.command.implementations.CloseMultilineComment;
import com.training.formatter.command.implementations.For;
import com.training.formatter.command.implementations.ForAfterChar;
import com.training.formatter.command.implementations.Ignore;
import com.training.formatter.command.implementations.Lexeme;
import com.training.formatter.command.implementations.LexemeOnNewLine;
import com.training.formatter.command.implementations.LexemeWithSpace;
import com.training.formatter.command.implementations.OpenBrace;
import com.training.formatter.command.implementations.OpenBraceOnNewLine;
import com.training.formatter.command.implementations.OpenBraceAfterChar;
import com.training.formatter.command.implementations.OpenBracket;
import com.training.formatter.command.implementations.OpenBracketAfterChar;
import com.training.formatter.command.implementations.OpenBracketOnNewLine;
import com.training.formatter.command.implementations.Semicolon;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepositoryFormatter implements ICommandRepositoryFormatter {
    Map<StatesPair<IState, String>, ICommandFormatter> commands;

    public CommandRepositoryFormatter() {
        commands = new HashMap<>();

        // start
        commands.put(new StatesPair<>(new State("start"), "char"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "openbracket"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "closebracket"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "openbrace"), new OpenBrace());
        commands.put(new StatesPair<>(new State("start"), "closebrace"), new CloseBrace());
        commands.put(new StatesPair<>(new State("start"), "closemultilinecomment"), new CloseMultilineComment());
        commands.put(new StatesPair<>(new State("start"), "newline"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("start"), "for"), new For());
        commands.put(new StatesPair<>(new State("start"), "onelinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "openmultilinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "quotemark"), new Lexeme());
        commands.put(new StatesPair<>(new State("start"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("start"), "spaces"), new Ignore());

        // char
        commands.put(new StatesPair<>(new State("char"), "char"), new Lexeme());
        commands.put(new StatesPair<>(new State("char"), "openbracket"), new OpenBracketAfterChar());
        commands.put(new StatesPair<>(new State("char"), "closebracket"), new Lexeme());
        commands.put(new StatesPair<>(new State("char"), "openbrace"), new OpenBraceAfterChar());
        commands.put(new StatesPair<>(new State("char"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("char"), "closemultilinecomment"), new CloseMultilineComment());
        commands.put(new StatesPair<>(new State("char"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("char"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("char"), "for"), new ForAfterChar());
        commands.put(new StatesPair<>(new State("char"), "onelinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("char"), "openmultilinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("char"), "quotemark"), new Lexeme());
        commands.put(new StatesPair<>(new State("char"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("char"), "spaces"), new Ignore());

        // openbracket
        commands.put(new StatesPair<>(new State("openbracket"), "char"), new Lexeme());
        commands.put(new StatesPair<>(new State("openbracket"), "openbracket"), new OpenBracket());
        commands.put(new StatesPair<>(new State("openbracket"), "closebracket"), new Lexeme());
        commands.put(new StatesPair<>(new State("openbracket"), "openbrace"), new OpenBracketAfterChar());
        commands.put(new StatesPair<>(new State("openbracket"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("openbracket"), "closemultilinecomment"), new CloseMultilineComment());
        commands.put(new StatesPair<>(new State("openbracket"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("openbracket"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("openbracket"), "for"), new ForAfterChar());
        commands.put(new StatesPair<>(new State("openbracket"), "onelinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("openbracket"), "openmultilinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("openbracket"), "quotemark"), new Lexeme());
        commands.put(new StatesPair<>(new State("openbracket"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("openbracket"), "spaces"), new Ignore());

        // space
        commands.put(new StatesPair<>(new State("space"), "char"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("space"), "openbracket"), new OpenBracket());
        commands.put(new StatesPair<>(new State("space"), "closebracket"), new Lexeme());
        commands.put(new StatesPair<>(new State("space"), "openbrace"), new OpenBraceAfterChar());
        commands.put(new StatesPair<>(new State("space"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("space"), "closemultilinecomment"), new CloseMultilineComment());
        commands.put(new StatesPair<>(new State("space"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("space"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("space"), "for"), new For());
        commands.put(new StatesPair<>(new State("space"), "onelinecomment"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("space"), "openmultilinecomment"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("space"), "quotemark"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("space"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("space"), "spaces"), new Ignore());
        
        // new line
        commands.put(new StatesPair<>(new State("newline"), "char"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "openbracket"), new OpenBracketOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "closebracket"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "openbrace"), new OpenBraceOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "closemultilinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("newline"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("newline"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("newline"), "for"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "onelinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("newline"), "openmultilinecomment"), new Lexeme());
        commands.put(new StatesPair<>(new State("newline"), "quotemark"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("newline"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("newline"), "spaces"), new Ignore());

        // true new line
        commands.put(new StatesPair<>(new State("truenewline"), "char"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "openbracket"), new OpenBracketOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "closebracket"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "openbrace"), new OpenBraceOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "closemultilinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "newline"), new Lexeme());
        commands.put(new StatesPair<>(new State("truenewline"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("truenewline"), "for"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "onelinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "openmultilinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "quotemark"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("truenewline"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("truenewline"), "spaces"), new Ignore());

        // second new line
        commands.put(new StatesPair<>(new State("secondnewline"), "char"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "openbracket"), new OpenBracketOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "closebracket"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "openbrace"), new OpenBraceOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "closemultilinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("secondnewline"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("secondnewline"), "for"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "onelinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "openmultilinecomment"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "quotemark"), new LexemeOnNewLine());
        commands.put(new StatesPair<>(new State("secondnewline"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("secondnewline"), "spaces"), new Ignore());

        // for
        commands.put(new StatesPair<>(new State("for"), "char"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("for"), "openbracket"), new OpenBracketAfterChar());
        commands.put(new StatesPair<>(new State("for"), "closebracket"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("for"), "openbrace"), new OpenBraceAfterChar());
        commands.put(new StatesPair<>(new State("for"), "closebrace"), new CloseBraceOnNewLine());
        commands.put(new StatesPair<>(new State("for"), "closemultilinecomment"), new CloseMultilineComment());
        commands.put(new StatesPair<>(new State("for"), "newline"), new Ignore());
        commands.put(new StatesPair<>(new State("for"), "semicolon"), new Semicolon());
        commands.put(new StatesPair<>(new State("for"), "for"), new ForAfterChar());
        commands.put(new StatesPair<>(new State("for"), "onelinecomment"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("for"), "openmultilinecomment"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("for"), "quotemark"), new LexemeWithSpace());
        commands.put(new StatesPair<>(new State("for"), "space"), new Ignore());
        commands.put(new StatesPair<>(new State("for"), "spaces"), new Ignore());

        // one line comment
        commands.put(new StatesPair<>(new State("onelinecomment"), null), new Lexeme());
        commands.put(new StatesPair<>(new State("onelinecomment"), "newline"), new Ignore());

        // multiline comment
        commands.put(new StatesPair<>(new State("multilinecomment"), null), new Lexeme());
        commands.put(new StatesPair<>(new State("multilinecomment"), "closemultilinecomment"), new Lexeme());

        // quotemark
        commands.put(new StatesPair<>(new State("quotemark"), null), new Lexeme());
        commands.put(new StatesPair<>(new State("quotemark"), "quotemark"), new Lexeme());
        commands.put(new StatesPair<>(new State("quotemark"), "newline"), new Ignore());

    }

    @Override
    public ICommandFormatter getCommand(IState state, IToken token) {
        ICommandFormatter command = commands.get(new StatesPair<>(state, token.getName()));

        if (command == null) {
            command = commands.get(new StatesPair<>(state, (String) null));
        }
        return command;
    }
}
