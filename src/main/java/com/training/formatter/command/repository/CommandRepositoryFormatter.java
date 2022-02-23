package com.training.formatter.command.repository;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.command.implementations.ClosingBraceWriter;
import com.training.formatter.command.implementations.ClosingMultilineCommentWriter;
import com.training.formatter.command.implementations.CodeWriter;
import com.training.formatter.command.implementations.DoubleQuoteEndWriter;
import com.training.formatter.command.implementations.Ignore;
import com.training.formatter.command.implementations.LexemeWriter;
import com.training.formatter.command.implementations.LexemeWriterLine;
import com.training.formatter.command.implementations.NewLineWriter;
import com.training.formatter.command.implementations.OpeningBraceWriter;
import com.training.formatter.command.implementations.SemicolonWriter;
import com.training.formatter.command.implementations.SimpleWriter;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepositoryFormatter implements ICommandRepositoryFormatter {
    Map<StatesPair, ICommandFormatter> commands;

    public CommandRepositoryFormatter() {
        commands = new HashMap<>();

        IState start = new State("START");
        IState code = new State("CODE");
        IState lineComment = new State("LINE_COMMENT");
        IState multilineComment = new State("MULTILINE_COMMENT");
        IState stringLiteral = new State("STRING_LITERAL");
        IState newLine = new State("NEW_LINE");

        //State - Start
        commands.put(new StatesPair(start, "OPENING_BRACE"), new LexemeWriterLine());
        commands.put(new StatesPair(start, "CLOSING_BRACE"), new LexemeWriterLine());
        commands.put(new StatesPair(start, "SEMICOLON"), new LexemeWriterLine());
        commands.put(new StatesPair(start, "SLASH"), new LexemeWriter());
        commands.put(new StatesPair(start, "NEW_LINE"), new Ignore());
        commands.put(new StatesPair(start, "SPACE"), new Ignore());
        commands.put(new StatesPair(start, "DOUBLE_QUOTE"), new LexemeWriter());
        commands.put(new StatesPair(start, "COMMON"), new LexemeWriter());
        commands.put(new StatesPair(start, "LINE_COMMENT"), new LexemeWriter());
        commands.put(new StatesPair(start, "OPENING_MULTILINE_COMMENT"), new LexemeWriter());
        commands.put(new StatesPair(start, "CLOSING_MULTILINE_COMMENT"), new LexemeWriterLine());
        //State - Code
        commands.put(new StatesPair(code, "OPENING_BRACE"), new OpeningBraceWriter());
        commands.put(new StatesPair(code, "CLOSING_BRACE"), new ClosingBraceWriter());
        commands.put(new StatesPair(code, "SEMICOLON"), new SemicolonWriter());
        commands.put(new StatesPair(code, "SLASH"), new CodeWriter());
        commands.put(new StatesPair(code, "NEW_LINE"), new Ignore());
        commands.put(new StatesPair(code, "SPACE"), new Ignore());
        commands.put(new StatesPair(code, "DOUBLE_QUOTE"), new CodeWriter());
        commands.put(new StatesPair(code, "COMMON"), new CodeWriter());
        commands.put(new StatesPair(code, "LINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(code, "OPENING_MULTILINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(code, "CLOSING_MULTILINE_COMMENT"), new ClosingMultilineCommentWriter());

        //State - LineComment
        commands.put(new StatesPair(lineComment, "OPENING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "CLOSING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "SEMICOLON"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "SLASH"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair(lineComment, "SPACE"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "DOUBLE_QUOTE"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "COMMON"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "LINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "OPENING_MULTILINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(lineComment, "CLOSING_MULTILINE_COMMENT"), new SimpleWriter());

        //State - Multiline Comment
        commands.put(new StatesPair(multilineComment, "OPENING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "CLOSING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "SEMICOLON"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "SLASH"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "NEW_LINE"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "SPACE"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "DOUBLE_QUOTE"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "COMMON"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "LINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "OPENING_MULTILINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(multilineComment, "CLOSING_MULTILINE_COMMENT"), new ClosingMultilineCommentWriter());

        //State - String Literal
        commands.put(new StatesPair(stringLiteral, "OPENING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "CLOSING_BRACE"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "SEMICOLON"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "SLASH"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair(stringLiteral, "SPACE"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "DOUBLE_QUOTE"), new DoubleQuoteEndWriter());
        commands.put(new StatesPair(stringLiteral, "COMMON"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "LINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "OPENING_MULTILINE_COMMENT"), new SimpleWriter());
        commands.put(new StatesPair(stringLiteral, "CLOSING_MULTILINE_COMMENT"), new SimpleWriter());

        //State - New line
        commands.put(new StatesPair(newLine, "OPENING_BRACE"), new LexemeWriterLine());
        commands.put(new StatesPair(newLine, "CLOSING_BRACE"), new LexemeWriterLine());
        commands.put(new StatesPair(newLine, "SEMICOLON"), new LexemeWriterLine());
        commands.put(new StatesPair(newLine, "SLASH"), new LexemeWriter());
        commands.put(new StatesPair(newLine, "NEW_LINE"), new Ignore());
        commands.put(new StatesPair(newLine, "SPACE"), new Ignore());
        commands.put(new StatesPair(newLine, "DOUBLE_QUOTE"), new LexemeWriter());
        commands.put(new StatesPair(newLine, "COMMON"), new LexemeWriter());
        commands.put(new StatesPair(newLine, "LINE_COMMENT"), new LexemeWriter());
        commands.put(new StatesPair(newLine, "OPENING_MULTILINE_COMMENT"), new LexemeWriter());
        commands.put(new StatesPair(newLine, "CLOSING_MULTILINE_COMMENT"), new LexemeWriter());
    }

    @Override
    public ICommandFormatter getCommand(IState state, IToken token) {
        return commands.get(new StatesPair(state, token.getName()));
    }
}
