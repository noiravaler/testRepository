package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.storage.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandDto;

@Slf4j
public class EditCommand implements Command {
    private static final String NAME = "edit";
    private final ITaskDao tasks;
    private CommandDto command;

    public EditCommand(ITaskDao tasks) {
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
        String description = command.getCommandLine();
        if (index == null || description == null)
            throw new IncorrectTaskException();

        if (tasks.get(index) == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда edit {} {}", index, description);
        tasks.get(index).setDescription(description);
    }
}