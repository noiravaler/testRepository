package org.example.todo.commands;

import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.models.CommandDto;

public interface Command {

    void setCommand(CommandDto command);

    void execute() throws IncorrectTaskException, TaskNotFoundException;
}
