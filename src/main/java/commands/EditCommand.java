package commands;

import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EditCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public EditCommand(Map<Integer, Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public void execute() throws IncorrectTaskException, TaskNotFoundException {
        Pattern pattern = Pattern.compile("([1-9]+)(\\s.+)");
        Matcher m = pattern.matcher(description);
        if (!m.find())
            throw new IncorrectTaskException();

        int index = Integer.parseInt(m.group(1));

        if (!tasks.containsKey(index))
            throw new TaskNotFoundException(index);

        log.debug("Выполняется команда edit " + description);
        tasks.get(index).setDescription(m.group(2));
        log.debug("Команда edit {} успешно выполнена", description);
    }
}