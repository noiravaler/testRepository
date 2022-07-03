package org.example.todo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.data.CommandData;
import org.example.todo.data.Task;
import org.example.todo.storage.ITaskDao;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Component
public class AddCommand implements Command {
    private static final String NAME = "add";
    private final ITaskDao tasks;
    private CommandData command;

    public AddCommand(ITaskDao tasks) {
        this.tasks = tasks;
        this.command = new CommandData();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setExecutedCommand(CommandData executedCommand) {
        this.command = executedCommand;
    }

    @Override
    public Stream<Task> execute() throws IncorrectTaskException {
        String description = command.getFullCommandLine();
        if (description == null)
            throw new IncorrectTaskException();
        log.debug("Выполняется команда add {}", description);
        tasks.add(new Task(description));
        return Stream.empty();
    }
}
