package com.training.formatter.command.repository;

import com.training.formatter.command.ICommandFormatter;
import com.training.lexer.token.IToken;
import com.training.state.IState;

public interface ICommandRepositoryFormatter {
    ICommandFormatter getCommand(IState state, IToken character);
}