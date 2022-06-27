package commands;

import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.util.Map;

@Slf4j
public class ToggleCommand implements Command {

    @Override
    public void execute(Map<Integer, Task> tasks, String command) {
        log.debug("Выполняется команда " + command);
        int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (checkTaskExists(index)) {
            tasks.get(index).setComplete(!tasks.get(index).isComplete());
        }
    }
}
