package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.storage.ITaskDao;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.data.CommandDto;
import org.example.todo.data.Task;

@Slf4j
public class AddCommand implements Command {
    private static final String NAME = "add";
    private final ITaskDao tasks;
    private CommandDto command;

    public AddCommand(ITaskDao tasks) {
        this.tasks = tasks;
        this.command = new CommandDto();
    }

    @Override
    public String getName() {
        return NAME;
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
        log.debug("Выполняется команда add {}", description);
        tasks.add(new Task(description));
    }
}
