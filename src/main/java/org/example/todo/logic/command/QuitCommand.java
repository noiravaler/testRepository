package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.data.CommandDto;

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