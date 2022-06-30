import exceptions.IncorrectTaskException;
import exceptions.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import models.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Main {
    static Map<Integer, Task> tasks = new LinkedHashMap<>();

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(tasks);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Введите команду");
                String commandLine = reader.readLine();
                try {
                    taskManager.executeCommand(commandLine);
                } catch (IncorrectTaskException | TaskNotFoundException ex) {
                    log.error(ex.getLocalizedMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при вводе данных. Введите команду еще раз");
        }
    }
}
