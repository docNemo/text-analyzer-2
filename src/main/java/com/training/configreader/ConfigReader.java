package com.training.configreader;

import com.training.exceptions.CouldNotCreateCommandRepository;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigReader implements IConfigReader {
    @Override
    public List<String[]> getListActionsState(final String pathToConfig, final boolean isGettingCommand) {
        try (InputStream file = new FileInputStream(pathToConfig)) {
            List<String[]> actions = new ArrayList<>();

            Yaml yaml = new Yaml();
            List statesDefs = yaml.load(file);

            for (Object stateDefObject : statesDefs) {
                Map stateDef = (Map) stateDefObject;
                String stateName = stateDef.get("state").toString();
                List actionsDefs = (List) stateDef.get("actions");

                for (Object actionDefObject : actionsDefs) {
                    Map actionDef = (Map) actionDefObject;
                    String input = (String) actionDef.get("input");

                    String[] action = new String[3];
                    action[0] = stateName;
                    action[1] = input;

                    if (isGettingCommand) {
                        action[2] = actionDef.get("command").toString();
                    } else {
                        action[2] = (String) actionDef.get("state");
                    }

                    actions.add(action);
                }

            }

            return actions;

        } catch (FileNotFoundException e) {
            throw new CouldNotCreateCommandRepository("Not found file: " + pathToConfig, e);
        } catch (IOException e) {
            throw new CouldNotCreateCommandRepository("Could  not read file: " + pathToConfig, e);
        }
    }
}
