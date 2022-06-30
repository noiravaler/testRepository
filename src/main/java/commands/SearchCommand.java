package commands;

import exceptions.IncorrectTaskException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class SearchCommand implements Command {
    private final Map<Integer, Task> tasks;
    private final String description;

    public SearchCommand(Map<Integer, Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public void execute() throws IncorrectTaskException {
        if (description.isEmpty())
            throw new IncorrectTaskException();

        Map<Integer, Task> sortedList = tasks.entrySet().stream()
                .filter(t -> t.getValue().getDescription().contains(description))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!sortedList.isEmpty()) {
            log.debug("Выполняется команда search " + description);
            sortedList.forEach(this::printTask);
        } else
            System.out.println("По вашему запросу ничего не найдено");
    }

    private void printTask(int index, Task task) {
        System.out.printf("%d. [%s] %s%n", index, task.isComplete() ? "x" : " ", task.getDescription());
    }
}