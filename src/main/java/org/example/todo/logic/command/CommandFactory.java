package org.example.todo.logic.command;

import org.example.todo.storage.ITaskDao;
import org.example.todo.view.ITaskPrinter;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commandList;

    public CommandFactory(ITaskDao tasks, ITaskPrinter printer) {
        commandList = new HashMap<>();

        Command addCommand = new AddCommand(tasks);
        commandList.put(addCommand.getName(), addCommand);

        Command toggleCommand = new ToggleCommand(tasks);
        commandList.put(toggleCommand.getName(), toggleCommand);

        Command deleteCommand = new DeleteCommand(tasks);
        commandList.put(deleteCommand.getName(), deleteCommand);

        Command editCommand = new EditCommand(tasks);
        commandList.put(editCommand.getName(), editCommand);

        Command printCommand = new PrintCommand(tasks, printer);
        commandList.put(printCommand.getName(), printCommand);

        Command searchCommand = new SearchCommand(tasks, printer);
        commandList.put(searchCommand.getName(), searchCommand);

        Command quitCommand = new QuitCommand();
        commandList.put(quitCommand.getName(), quitCommand);
    }

    public Map<String, Command> getCommandList() {
        return commandList;
    }
}
