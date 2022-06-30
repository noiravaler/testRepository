package commands;

import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class ToggleCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public ToggleCommand(Map<Integer, Task> tasks, String description) {
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

        log.debug("Выполняется команда toggle " + description);
        tasks.get(index).setComplete(!tasks.get(index).isComplete());
        log.debug("Команда toggle {} успешно выполнена", description);
    }
}