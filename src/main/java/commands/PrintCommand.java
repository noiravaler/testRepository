package commands;

import exceptions.IncorrectTaskException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class PrintCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public PrintCommand(Map<Integer, Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public void execute() throws IncorrectTaskException {
        if (tasks.isEmpty()) {
            log.warn("Задачи для печати отсутствуют");
            return;
        }
        if (!description.isEmpty() && !description.equals("all"))
            throw new IncorrectTaskException();

        log.debug("Выполняется команда print " + description);
        tasks.entrySet().stream().filter(t -> "all".equals(description) || !t.getValue().isComplete()).forEach(t -> printTask(t.getKey(), t.getValue()));
    }

    private void printTask(int index, Task task) {
        System.out.printf("%d. [%s] %s%n", index, task.isComplete() ? "x" : " ", task.getDescription());
    }
}