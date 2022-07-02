package org.example.todo;

import org.example.todo.commands.Command;
import org.example.todo.commands.CommandFactory;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.models.CommandDto;
import org.example.todo.util.ITaskPrinter;
import org.example.todo.util.TaskParser;

import java.util.Map;

public class ConsoleTaskManager {
    private final Map<String, Command> commandList;

    public ConsoleTaskManager(ITaskDao tasks, ITaskPrinter printer) {
        this.commandList = new CommandFactory(tasks, printer).getCommandList();
    }

    public void executeCommand(String commandLine) throws IncorrectTaskException, TaskNotFoundException {
        CommandDto commandDto = TaskParser.parseCommand(commandLine);
        Command command = commandList.get(commandDto.getCommandLine());
        if (command == null)
            throw new IncorrectTaskException();
        command.setCommand(commandDto);
        command.execute();
    }
}
