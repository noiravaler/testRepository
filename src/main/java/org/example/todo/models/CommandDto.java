package org.example.todo.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommandDto {
    private String commandName;
    private Long taskIndex;
    private String commandLine;
    private String fullCommandLine;
}
