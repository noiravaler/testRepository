package org.example.todo.core;

import org.example.todo.logic.command.*;
import org.example.todo.storage.ITaskDao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commandList;

    public CommandFactory(ITaskDao tasks) {
        Map<String, Command> map = new HashMap<>();

        Command addCommand = new AddCommand(tasks);
        map.put(addCommand.getName(), addCommand);

        Command toggleCommand = new ToggleCommand(tasks);
        map.put(toggleCommand.getName(), toggleCommand);

        Command deleteCommand = new DeleteCommand(tasks);
        map.put(deleteCommand.getName(), deleteCommand);

        Command editCommand = new EditCommand(tasks);
        map.put(editCommand.getName(), editCommand);

        Command printCommand = new PrintCommand(tasks);
        map.put(printCommand.getName(), printCommand);

        Command searchCommand = new SearchCommand(tasks);
        map.put(searchCommand.getName(), searchCommand);

        Command quitCommand = new QuitCommand();
        map.put(quitCommand.getName(), quitCommand);

        this.commandList = Collections.unmodifiableMap(map);
    }

    public Map<String, Command> getCommandList() {
        return commandList;
    }
}
