package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.lexer.state.IState;
import com.training.lexer.state.StatesPair;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository implements ICommandRepository {
    Map<StatesPair, ICommand> commands;

    public CommandRepository() {
        commands = new HashMap<>();

//        commands.put(new StatesPair());TODO
    }

    @Override
    public ICommand getCommand(IState state, char character) {

//        return commands.get(new StatesPair(state, character));
        return null;
    }
}
