package com.training.formatter.command.repository;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.command.implementations.ClosingBraceWriter;
import com.training.formatter.command.implementations.ClosingBraceWriterAfterCode;
import com.training.formatter.command.implementations.ClosingBraceWriterWithIndent;
import com.training.formatter.command.implementations.Ignore;
import com.training.formatter.command.implementations.NewLineWriter;
import com.training.formatter.command.implementations.OpeningBraceWriter;
import com.training.formatter.command.implementations.OpeningBraceWriterAfterCode;
import com.training.formatter.command.implementations.OpeningBraceWriterWithIndent;
import com.training.formatter.command.implementations.Writer;
import com.training.formatter.command.implementations.WriterAfterSpace;
import com.training.formatter.command.implementations.WriterWithIndentAndNewLine;
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

        IState start = new State("START");
        IState code = new State("CODE");
        IState lineComment = new State("LINE_COMMENT");
        IState multilineComment = new State("MULTILINE_COMMENT");
        IState stringLiteral = new State("STRING_LITERAL");
        IState newLine = new State("NEW_LINE");
        IState trueNewLine = new State("TRUE_NEW_LINE");

        //State - Start
        commands.put(new StatesPair<>(start, "OPENING_BRACE"), new OpeningBraceWriter());
        commands.put(new StatesPair<>(start, "CLOSING_BRACE"), new ClosingBraceWriter());
        commands.put(new StatesPair<>(start, "SEMICOLON"), new Writer());
        commands.put(new StatesPair<>(start, "SLASH"), new Writer());
        commands.put(new StatesPair<>(start, "NEW_LINE"), new Ignore());
        commands.put(new StatesPair<>(start, "SPACE"), new Ignore());
        commands.put(new StatesPair<>(start, "DOUBLE_QUOTE"), new Writer());
        commands.put(new StatesPair<>(start, "COMMON"), new Writer());
        commands.put(new StatesPair<>(start, "LINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(start, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(start, "CLOSING_MULTILINE_COMMENT"), new Writer());
        //State - Code
        commands.put(new StatesPair<>(code, "OPENING_BRACE"), new OpeningBraceWriterAfterCode());
        commands.put(new StatesPair<>(code, "CLOSING_BRACE"), new ClosingBraceWriterAfterCode());
        commands.put(new StatesPair<>(code, "SEMICOLON"), new Writer());
        commands.put(new StatesPair<>(code, "SLASH"), new WriterAfterSpace());
        commands.put(new StatesPair<>(code, "NEW_LINE"), new Ignore());
        commands.put(new StatesPair<>(code, "SPACE"), new Ignore());
        commands.put(new StatesPair<>(code, "DOUBLE_QUOTE"), new WriterAfterSpace());
        commands.put(new StatesPair<>(code, "COMMON"), new WriterAfterSpace());
        commands.put(new StatesPair<>(code, "LINE_COMMENT"), new WriterAfterSpace());
        commands.put(new StatesPair<>(code, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(code, "CLOSING_MULTILINE_COMMENT"), new Writer());

        //State - LineComment
        commands.put(new StatesPair<>(lineComment, "OPENING_BRACE"), new Writer());
        commands.put(new StatesPair<>(lineComment, "CLOSING_BRACE"), new Writer());
        commands.put(new StatesPair<>(lineComment, "SEMICOLON"), new Writer());
        commands.put(new StatesPair<>(lineComment, "SLASH"), new Writer());
        commands.put(new StatesPair<>(lineComment, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair<>(lineComment, "SPACE"), new Writer());
        commands.put(new StatesPair<>(lineComment, "DOUBLE_QUOTE"), new Writer());
        commands.put(new StatesPair<>(lineComment, "COMMON"), new Writer());
        commands.put(new StatesPair<>(lineComment, "LINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(lineComment, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(lineComment, "CLOSING_MULTILINE_COMMENT"), new Writer());

        //State - Multiline Comment
        commands.put(new StatesPair<>(multilineComment, "OPENING_BRACE"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "CLOSING_BRACE"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "SEMICOLON"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "SLASH"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "NEW_LINE"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "SPACE"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "DOUBLE_QUOTE"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "COMMON"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "LINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(multilineComment, "CLOSING_MULTILINE_COMMENT"), new Writer());

        //State - String Literal
        commands.put(new StatesPair<>(stringLiteral, "OPENING_BRACE"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "CLOSING_BRACE"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "SEMICOLON"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "SLASH"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair<>(stringLiteral, "SPACE"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "DOUBLE_QUOTE"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "COMMON"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "LINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(stringLiteral, "CLOSING_MULTILINE_COMMENT"), new Writer());

        //State - New line
        commands.put(new StatesPair<>(newLine, "OPENING_BRACE"), new OpeningBraceWriterWithIndent());
        commands.put(new StatesPair<>(newLine, "CLOSING_BRACE"), new ClosingBraceWriterWithIndent());
        commands.put(new StatesPair<>(newLine, "SEMICOLON"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(newLine, "SLASH"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(newLine, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair<>(newLine, "SPACE"), new Ignore());
        commands.put(new StatesPair<>(newLine, "DOUBLE_QUOTE"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(newLine, "COMMON"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(newLine, "LINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(newLine, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(newLine, "CLOSING_MULTILINE_COMMENT"), new Writer());

        //State - True New line
        commands.put(new StatesPair<>(trueNewLine, "OPENING_BRACE"), new OpeningBraceWriterWithIndent());
        commands.put(new StatesPair<>(trueNewLine, "CLOSING_BRACE"), new ClosingBraceWriterWithIndent());
        commands.put(new StatesPair<>(trueNewLine, "SEMICOLON"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(trueNewLine, "SLASH"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(trueNewLine, "NEW_LINE"), new NewLineWriter());
        commands.put(new StatesPair<>(trueNewLine, "SPACE"), new Ignore());
        commands.put(new StatesPair<>(trueNewLine, "DOUBLE_QUOTE"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(trueNewLine, "COMMON"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(trueNewLine, "LINE_COMMENT"), new WriterWithIndentAndNewLine());
        commands.put(new StatesPair<>(trueNewLine, "OPENING_MULTILINE_COMMENT"), new Writer());
        commands.put(new StatesPair<>(trueNewLine, "CLOSING_MULTILINE_COMMENT"), new Writer());
    }

    @Override
    public ICommandFormatter getCommand(IState state, IToken token) {
        return commands.get(new StatesPair<>(state, token.getName()));
    }
}
