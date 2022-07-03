package org.example.todo.core;

import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.logic.command.Command;
import org.example.todo.storage.ITaskDao;
import org.example.todo.view.ITaskPrinter;
import org.example.todo.view.TaskParser;

import java.util.Map;
import java.util.stream.Stream;

public class ConsoleTaskManager {
    private final Map<String, Command> commandList;
    private final ITaskPrinter printer;

    public ConsoleTaskManager(ITaskDao tasks, ITaskPrinter printer) {
        this.printer = printer;
        this.commandList = new CommandFactory(tasks).getCommandList();
    }

    public void executeCommand(String commandLine) throws IncorrectTaskException, TaskNotFoundException {
        CommandData commandDto = TaskParser.parseCommand(commandLine);
        Command command = commandList.get(commandDto.getCommandLine());
        if (command == null)
            throw new IncorrectTaskException();
        command.setExecutedCommand(commandDto);
        Stream<Task> taskStream = command.execute();
        printer.print(taskStream);
    }
}
