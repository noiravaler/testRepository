package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.models.CommandDto;

@Slf4j
public class QuitCommand implements Command {
    private static final String NAME = "quit";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setCommand(CommandDto command) {

    }

    @Override
    public void execute() {
        System.exit(0);
    }
}