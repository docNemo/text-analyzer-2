package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.lexer.command.implementations.AppendPostpone;
import com.training.lexer.command.implementations.Char;
import com.training.lexer.command.implementations.CloseBrace;
import com.training.lexer.command.implementations.CloseBracket;
import com.training.lexer.command.implementations.CloseMultiLineComment;
import com.training.lexer.command.implementations.Newline;
import com.training.lexer.command.implementations.OneLineComment;
import com.training.lexer.command.implementations.OpenBrace;
import com.training.lexer.command.implementations.OpenBracket;
import com.training.lexer.command.implementations.OpenMultiLineComment;
import com.training.lexer.command.implementations.Quotemark;
import com.training.lexer.command.implementations.Semicolon;
import com.training.lexer.command.implementations.Space;
import com.training.lexer.command.implementations.Spaces;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository implements ICommandRepository {

    private final Map<StatesPair<IState, Character>, ICommand> commands;

    public CommandRepository() {
        commands = new HashMap<>();

        //Start
        commands.put(new StatesPair<>(new State("start"), null), new Char());
        commands.put(new StatesPair<>(new State("start"), ';'), new Semicolon());
        commands.put(new StatesPair<>(new State("start"), '\n'), new Newline());
        commands.put(new StatesPair<>(new State("start"), '('), new OpenBracket());
        commands.put(new StatesPair<>(new State("start"), ')'), new CloseBracket());
        commands.put(new StatesPair<>(new State("start"), '}'), new CloseBrace());
        commands.put(new StatesPair<>(new State("start"), '{'), new OpenBrace());
        commands.put(new StatesPair<>(new State("start"), ' '), new Space());
        commands.put(new StatesPair<>(new State("start"), '/'), new Char());
        commands.put(new StatesPair<>(new State("start"), '"'), new Quotemark());

        //spacing
        commands.put(new StatesPair<>(new State("spacing"), null), new AppendPostpone());
        commands.put(new StatesPair<>(new State("spacing"), ' '), new Spaces());

        //slash
        commands.put(new StatesPair<>(new State("slash"), null), new AppendPostpone());
        commands.put(new StatesPair<>(new State("slash"), '*'), new OpenMultiLineComment());
        commands.put(new StatesPair<>(new State("slash"), '/'), new OneLineComment());

        //asterisk
        commands.put(new StatesPair<>(new State("asterisk"), null), new AppendPostpone());
        commands.put(new StatesPair<>(new State("asterisk"), '/'), new CloseMultiLineComment());


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
