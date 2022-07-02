package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.models.CommandDto;

@Slf4j
public class DeleteCommand implements Command {
    private static final String NAME = "delete";
    private final ITaskDao tasks;
    private CommandDto command;

    public DeleteCommand(ITaskDao tasks) {
        this.tasks = tasks;
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
    public void execute() throws IncorrectTaskException, TaskNotFoundException {
        Long index = command.getTaskIndex();
        if (index == null)
            throw new IncorrectTaskException();

        if (tasks.get(index) == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда delete {}", index);
        tasks.delete(index);
    }
}