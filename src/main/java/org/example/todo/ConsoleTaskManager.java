package org.example.todo;

import org.example.todo.commands.*;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.models.CommandDto;
import org.example.todo.util.ITaskPrinter;
import org.example.todo.util.TaskParser;

import java.util.HashMap;
import java.util.Map;

public class ConsoleTaskManager {
    private final ITaskDao tasks;
    private final ITaskPrinter printer;

    public ConsoleTaskManager(ITaskDao tasks, ITaskPrinter printer) {
        this.tasks = tasks;
        this.printer = printer;
    }

    public void executeCommand(String commandLine) throws IncorrectTaskException, TaskNotFoundException {
        CommandDto commandDto = TaskParser.parseCommand(commandLine);
        Command command = defineCommand(commandDto.getCommandName());
        command.setCommand(commandDto);
        command.execute();
    }

    private Command defineCommand(String commandName) throws IncorrectTaskException {
        Map<String, Command> commandList = new HashMap<String, Command>() {{
            put("add", new AddCommand(tasks));
            put("toggle", new ToggleCommand(tasks));
            put("delete", new DeleteCommand(tasks));
            put("edit", new EditCommand(tasks));
            put("print", new PrintCommand(tasks, printer));
            put("search", new SearchCommand(tasks, printer));
            put("quit", new QuitCommand());
        }};
        Command command = commandList.get(commandName);
        if (command != null)
            return command;
        else
            throw new IncorrectTaskException();
    }
}
