package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.storage.ITaskDao;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Component
public class EditCommand implements Command {
    private static final String NAME = "edit";
    private final ITaskDao tasks;
    private CommandData command;

    public EditCommand(ITaskDao tasks) {
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
        String description = command.getCommandLine();
        if (index == null || description == null)
            throw new IncorrectTaskException();

        if (tasks.get(index) == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда edit {} {}", index, description);
        tasks.get(index).setDescription(description);
        return Stream.empty();
    }
}