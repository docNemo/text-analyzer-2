package com.training.configreader;

import java.util.List;

public interface IConfigReader {
    List<String[]> getListActionsState(final String pathToConfig, final boolean isGettingCommand);
}
