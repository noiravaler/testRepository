package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.storage.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.data.CommandDto;
import org.example.todo.data.Task;
import org.example.todo.view.ITaskPrinter;

import java.util.stream.Stream;

@Slf4j
public class SearchCommand implements Command {
    private static final String NAME = "search";
    private final ITaskDao tasks;
    private final ITaskPrinter taskPrinter;
    private CommandDto command;

    public SearchCommand(ITaskDao tasks, ITaskPrinter taskPrinter) {
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
        if (description.isEmpty())
            throw new IncorrectTaskException();

        log.debug("Выполняется команда search {}", description);
        Stream<Task> taskStream = tasks.find(description, false);
        taskPrinter.print(taskStream);
    }
}