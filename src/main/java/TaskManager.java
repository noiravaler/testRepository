import commands.AddCommand;
import commands.Command;
import models.Task;

import java.util.Map;

public class TaskManager {
    private final Map<Integer, Task> tasks;

    public TaskManager(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

public Command executeCommand(String commandLine){
    Command command = null;
    if (commandLine.matches("add .+")) {
        command = new AddCommand();
        command.execute(tasks, commandLine);
    } else if (commandLine.matches("print.*")) {
        print(command);
    } else if (command.matches("toggle [1-9]\\d*")) {
        toggle(command);
    } else if (command.matches("delete [1-9]\\d*")) {
        delete(command);
    } else if (command.matches("edit [1-9]\\d* .+")) {
        edit(command);
    } else if (command.matches("search .+")) {
        search(command);
    } else if (command.equals("quit")) {
        break;
    } else {
        log.error("Команда указана некорректно. Введите команду еще раз");
        System.out.println("Доступные команды: " +
                "add <описание задачи>\n" +
                "print [all]\n" +
                "search <substring>\n" +
                "toggle <идентификатор задачи>\n" +
                "delete <идентификатор задачи>\n" +
                "edit <идентификатор задачи> <новое значение>\n" +
                "quit");
    }
}
}
