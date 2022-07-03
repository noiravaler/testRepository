package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.storage.ITaskDao;

import java.util.stream.Stream;

@Slf4j
public class ToggleCommand implements Command {
    private static final String NAME = "toggle";
    private final ITaskDao tasks;
    private CommandData command;

    public ToggleCommand(ITaskDao tasks) {
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
        if (index == null) throw new IncorrectTaskException();

        Task task = tasks.get(index);
        if (task == null) throw new TaskNotFoundException(index);

        log.debug("Выполняется команда toggle {}", index);
        task.setComplete(!task.isComplete());
        return Stream.empty();
    }
}