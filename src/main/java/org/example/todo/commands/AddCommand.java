package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.dao.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.models.CommandDto;

@Slf4j
public class AddCommand implements Command {
    private final ITaskDao tasks;
    private CommandDto command;

    public AddCommand(ITaskDao tasks) {
        this.tasks = tasks;
        this.command = new CommandDto();
    }

    @Override
    public void setCommand(CommandDto command) {
        this.command = command;
    }

    @Override
    public void execute() throws IncorrectTaskException {
        String description = command.getFullCommandLine();
        if (description == null)
            throw new IncorrectTaskException();
        log.debug("Выполняется команда add " + description);
        tasks.add(description);
        log.debug("Команда add {} успешно выполнена", description);
    }
}
