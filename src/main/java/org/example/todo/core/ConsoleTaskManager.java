package org.example.todo.core;

import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.logic.command.Command;
import org.example.todo.view.ITaskPrinter;
import org.example.todo.view.TaskParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ConsoleTaskManager {
    private final Map<String, Command> commandList;
    private final ITaskPrinter printer;

    public ConsoleTaskManager(List<Command> commands, ITaskPrinter printer) {
        this.printer = printer;
        commandList = commands.stream().collect(Collectors.toMap(Command::getName, Function.identity()));
    }

    public void executeCommand(String commandLine) throws IncorrectTaskException, TaskNotFoundException {
        CommandData commandDto = TaskParser.parseCommand(commandLine);
        Command command = commandList.get(commandDto.getCommandName());
        if (command == null)
            throw new IncorrectTaskException();
        command.setExecutedCommand(commandDto);
        Stream<Task> taskStream = command.execute();
        printer.print(taskStream);
    }
}
