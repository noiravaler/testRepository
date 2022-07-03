package org.example.todo.view;

import org.example.todo.data.CommandData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("(?<command>\\w+)(?:\\s(?<args>(?:(?<id>\\d{1,19})\\b)?(?<params>.*)))?");

    public static CommandData parseCommand(String commandLine) {
        Matcher matcher = COMMAND_TEMPLATE.matcher(commandLine);
        CommandData command = new CommandData();
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
