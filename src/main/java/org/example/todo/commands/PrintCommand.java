package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.models.CommandDto;
import org.example.todo.models.Task;
import org.example.todo.util.ITaskPrinter;

import java.util.stream.Stream;

@Slf4j
public class PrintCommand implements Command {
    private static final String NAME = "print";
    private final ITaskDao tasks;
    private final ITaskPrinter taskPrinter;
    private CommandDto command;

    public PrintCommand(ITaskDao tasks, ITaskPrinter taskPrinter) {
        this.tasks = tasks;
        this.taskPrinter = taskPrinter;
        this.command = new CommandDto();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setCommand(CommandDto command) {
        this.command = command;
    }

    @Override
    public void execute() throws IncorrectTaskException {
        String description = command.getFullCommandLine();
        if (description != null && !description.equals("all"))
            throw new IncorrectTaskException();

        log.debug("Выполняется команда print {}", description);
        Stream<Task> taskStream = tasks.find(null, description == null);
        taskPrinter.print(taskStream);
    }
}