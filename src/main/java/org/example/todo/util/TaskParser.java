package org.example.todo.util;

import org.example.todo.models.CommandDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("(?<command>\\w+)(?:\\s(?<args>(?:(?<id>\\d+)\\b)?(?<params>.*)))?");

    public static CommandDto parseCommand(String commandLine) {
        Matcher matcher = COMMAND_TEMPLATE.matcher(commandLine);
        CommandDto command = new CommandDto();
        if (matcher.find()) {
            command.setCommandName(matcher.group("command"))
                    .setCommandLine(matcher.group("params"))
                    .setFullCommandLine(matcher.group("args"));
            String id = matcher.group("id");
            if (id != null)
                command.setTaskIndex(Long.parseLong(id));
        }
        return command;
    }
}
