package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.storage.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandDto;
import org.example.todo.data.Task;

@Slf4j
public class ToggleCommand implements Command {
    private static final String NAME = "toggle";
    private final ITaskDao tasks;
    private CommandDto command;

    public ToggleCommand(ITaskDao tasks) {
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

        Task task = tasks.get(index);
        if (task == null)
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда toggle {}", index);
        task.setComplete(!task.isComplete());
    }
}