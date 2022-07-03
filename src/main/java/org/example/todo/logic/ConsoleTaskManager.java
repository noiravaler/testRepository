package org.example.todo.logic;

import org.example.todo.data.CommandDto;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.logic.command.Command;
import org.example.todo.logic.command.CommandFactory;
import org.example.todo.storage.ITaskDao;
import org.example.todo.view.ITaskPrinter;
import org.example.todo.view.TaskParser;

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
