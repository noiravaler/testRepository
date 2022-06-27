package commands;

import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class AddCommand implements Command {

    @Override
    public void execute(Map<Integer, Task> tasks, String command) {
        log.debug("Выполняется команда " + command);
        String description = command.replace("add", "").trim();
        Task task = new Task(description);
        Integer lastIndex = tasks.keySet().stream().max(Integer::compareTo).orElse(1);
        tasks.put(lastIndex, task);
    }
}
