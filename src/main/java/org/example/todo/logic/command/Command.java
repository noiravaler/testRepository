package org.example.todo.logic.command;

import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;

import java.util.stream.Stream;

public interface Command {

    String getName();

    void setExecutedCommand(CommandData executedCommand);

    Stream<Task> execute() throws IncorrectTaskException, TaskNotFoundException;
}
