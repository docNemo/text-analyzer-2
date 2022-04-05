package com.training.formatter.command.repository;

import com.training.configreader.ConfigReader;
import com.training.configreader.IConfigReader;
import com.training.exceptions.CouldNotCreateCommand;
import com.training.formatter.command.ICommandFormatter;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRepositoryFormatter implements ICommandRepositoryFormatter {
    private static final String COMMAND_PACKAGE = "com.training.formatter.command.implementations";
    Map<StatesPair<IState, String>, ICommandFormatter> commands;

    public CommandRepositoryFormatter(final String pathToConfig) {
        commands = new HashMap<>();

        IConfigReader configReader = new ConfigReader();
        List<String[]> actions = configReader.getListActionsState(pathToConfig, true);

        for (String[] action : actions) {
            String state = action[0];
            String input = action[1];
            String commandName = action[2];
            commands.put(new StatesPair<>(new State(state), input), createCommand(commandName));
        }
    }

    private ICommandFormatter createCommand(final String commandName) {
        String fullName = COMMAND_PACKAGE + "." + commandName;
        try {
            return (ICommandFormatter) Class.forName(fullName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new CouldNotCreateCommand("Not found file: " + fullName, e);
        } catch (InvocationTargetException e) {
            throw new CouldNotCreateCommand("Exception in constructor of command: " + fullName, e);
        } catch (InstantiationException e) {
            throw new CouldNotCreateCommand("Can't be create instance of command: " + fullName, e);
        } catch (IllegalAccessException e) {
            throw new CouldNotCreateCommand("Illegal access for file: " + fullName, e);
        } catch (NoSuchMethodException e) {
            throw new CouldNotCreateCommand("Not found method for create command: " + fullName, e);
        }

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
