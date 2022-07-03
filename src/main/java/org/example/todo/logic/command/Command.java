package org.example.todo.logic.command;

import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandDto;

public interface Command {

    String getName();

    void setCommand(CommandDto command);

    void execute() throws IncorrectTaskException, TaskNotFoundException;
}
