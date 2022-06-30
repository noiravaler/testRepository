package commands;

import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;

public interface Command {

    void execute() throws IncorrectTaskException, TaskNotFoundException;
}
