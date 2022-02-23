package com.training.lexer.command.repository;

import com.training.lexer.command.ICommand;
import com.training.state.IState;

public interface ICommandRepository {
    ICommand getCommand(IState state, char character);
}
