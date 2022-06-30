package commands;

import exceptions.IncorrectTaskException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class AddCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public AddCommand(Map<Integer, Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public void execute() throws IncorrectTaskException {
        if (description.isEmpty())
            throw new IncorrectTaskException();
        log.debug("Выполняется команда add " + description);
        Task task = new Task(description);
        Integer lastIndex = tasks.keySet().stream().max(Integer::compareTo).orElse(1);
        tasks.put(lastIndex, task);
        log.debug("Команда add {} успешно выполнена", description);
    }
}
