package com.training.formatter.command.repository;

import com.training.exceptions.CouldNotCreateCommand;
import com.training.exceptions.CouldNotCreateCommandRepository;
import com.training.formatter.command.ICommandFormatter;
import com.training.lexer.token.IToken;
import com.training.state.IState;
import com.training.state.State;
import com.training.state.StatesPair;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRepositoryFormatter implements ICommandRepositoryFormatter {
    private static final String COMMAND_PACKAGE = "com.training.formatter.command.implementations";
    Map<StatesPair<IState, String>, ICommandFormatter> commands;

    public CommandRepositoryFormatter(final String pathToConfig) {
        commands = new HashMap<>();

        commands = new HashMap<>();

        try (InputStream file = new FileInputStream(pathToConfig)) {
            Yaml yaml = new Yaml();
            List statesDefs = yaml.load(file);

            for (Object stateDefObject : statesDefs) {
                Map stateDef = (Map) stateDefObject;
                String stateName = stateDef.get("state").toString();
                List actionsDefs = (List) stateDef.get("actions");

                for (Object actionDefObject : actionsDefs) {
                    Map actionDef = (Map) actionDefObject;
                    String commandName = actionDef.get("command").toString();
                    String input = (String) actionDef.get("input");
                    commands.put(new StatesPair<>(new State(stateName), input), createCommand(commandName));
                }

            }

        } catch (FileNotFoundException e) {
            throw new CouldNotCreateCommandRepository("Not found file: " + pathToConfig, e);
        } catch (IOException e) {
            throw new CouldNotCreateCommandRepository("Could  not read file: " + pathToConfig, e);
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
