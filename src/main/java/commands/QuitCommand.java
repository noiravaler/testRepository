package commands;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuitCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}