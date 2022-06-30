import commands.*;
import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;
import models.Task;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskManager {
    private final Map<Integer, Task> tasks;

    public TaskManager(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public void executeCommand(String commandLine) throws IncorrectTaskException, TaskNotFoundException {
        Command command = defineCommand(commandLine);
        command.execute();
    }

    private Command defineCommand(String commandLine) throws IncorrectTaskException {
        Pattern pattern = Pattern.compile("^(\\w+)(\\s.*|)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String command = matcher.group(2).trim();
            switch (matcher.group(1)) {
                case "add":
                    return new AddCommand(tasks, command);
                case "toggle":
                    return new ToggleCommand(tasks, command);
                case "delete":
                    return new DeleteCommand(tasks, command);
                case "edit":
                    return new EditCommand(tasks, command);
                case "print":
                    return new PrintCommand(tasks, command);
                case "search":
                    return new SearchCommand(tasks, command);
                case "quit":
                    return new QuitCommand();

                default:
                    throw new IncorrectTaskException();
            }
        } else
            throw new IncorrectTaskException();
    }
}
