package commands;

import models.Task;

import java.util.List;
import java.util.Map;

public interface Command {

    void execute(Map<Integer, Task> tasks, String command);
}
