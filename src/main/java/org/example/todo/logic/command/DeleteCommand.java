package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.storage.ITaskDao;

import java.util.stream.Stream;

@Slf4j
public class DeleteCommand implements Command {
    private static final String NAME = "delete";
    private final ITaskDao tasks;
    private CommandData command;

    public DeleteCommand(ITaskDao tasks) {
        this.tasks = tasks;
        this.command = new CommandData();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setExecutedCommand(CommandData executedCommand) {
        this.command = executedCommand;
    }

    @Override
    public Stream<Task> execute() throws IncorrectTaskException, TaskNotFoundException {
        Long index = command.getTaskIndex();
        if (index == null)
            throw new IncorrectTaskException();

        if (tasks.get(index) == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда delete {}", index);
        tasks.delete(index);
        return Stream.empty();
    }
}