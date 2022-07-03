package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;

import java.util.stream.Stream;

@Slf4j
public class QuitCommand implements Command {
    private static final String NAME = "quit";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setExecutedCommand(CommandData executedCommand) {

    }

    @Override
    public Stream<Task> execute() {
        System.exit(0);
        return Stream.empty();
    }
}