package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.models.CommandDto;

@Slf4j
public class QuitCommand implements Command {

    @Override
    public void setCommand(CommandDto command) {

    }

    @Override
    public void execute() {
        System.exit(0);
    }
}