package org.example.todo.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommandData {
    private String commandName;
    private Long taskIndex;
    private String commandLine;
    private String fullCommandLine;
}