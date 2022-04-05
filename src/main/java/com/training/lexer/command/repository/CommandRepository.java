package com.training.lexer.command.repository;

import com.training.configreader.ConfigReader;
import com.training.configreader.IConfigReader;
import com.training.exceptions.CouldNotCreateCommand;
import com.training.lexer.command.ICommand;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRepository implements ICommandRepository {
    static final String COMMAND_PACKAGE = "com.training.lexer.command.implementations";

    private static final byte CURRENT_STATE = 0;
    private static final byte INPUT = 1;
    private static final byte NAME_COMMAND = 2;

    private final Map<StatesPair<IState, String>, ICommand> commands;

    public CommandRepository(String pathToConfig) {
        commands = new HashMap<>();

        IConfigReader configReader = new ConfigReader();
        List<String[]> actions = configReader.getListActionsState(pathToConfig, true);

        for (String[] action : actions) {
            String state = action[CURRENT_STATE];
            String input = action[INPUT];
            String commandName = action[NAME_COMMAND];
            commands.put(new StatesPair<>(new State(state), input), createCommand(commandName));
        }
    }

    private ICommand createCommand(final String commandName) {
        String fullName = COMMAND_PACKAGE + "." + commandName;
        try {
            return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
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
    public ICommand getCommand(IState state, char character) {
        ICommand command = commands.get(new StatesPair<>(state, String.valueOf(character)));

        if (command == null) {
            command = commands.get(new StatesPair<>(state, (String) null));
        }

        return command;
    }
}
