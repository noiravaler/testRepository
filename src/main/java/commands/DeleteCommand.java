package commands;

import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class DeleteCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public DeleteCommand(Map<Integer, Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public void execute() throws IncorrectTaskException, TaskNotFoundException {
        if (!description.matches("[1-9]+$"))
            throw new IncorrectTaskException();

        int index = Integer.parseInt(description);

        if (!tasks.containsKey(index))
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда delete " + description);
        tasks.remove(index);
        log.debug("Команда delete {} успешно выполнена", description);
    }
}