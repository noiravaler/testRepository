package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.models.CommandDto;

@Slf4j
public class EditCommand implements Command {
    private final ITaskDao tasks;
    private CommandDto command;

    public EditCommand(ITaskDao tasks) {
        this.tasks = tasks;
        this.command = new CommandDto();
    }

    @Override
    public void setCommand(CommandDto command) {
        this.command = command;
    }

    @Override
    public void execute() throws IncorrectTaskException, TaskNotFoundException {
        Long index = command.getTaskIndex();
        String description = command.getCommandLine();
        if (index == null || description == null)
            throw new IncorrectTaskException();

        if (tasks.get(index) == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда edit {} {}", index, description);
        tasks.get(index).setDescription(description);
        log.debug("Команда edit {} {} успешно выполнена", index, description);
    }
}