package org.example.todo;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.exceptions.IncorrectTaskException;
import org.example.todo.exceptions.TaskNotFoundException;
import org.example.todo.logic.ConsoleTaskManager;
import org.example.todo.storage.ITaskDao;
import org.example.todo.storage.TaskDao;
import org.example.todo.view.ITaskPrinter;
import org.example.todo.view.TaskPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class Main {
    static ITaskDao tasks = TaskDao.getInstance();
    static ITaskPrinter printer = new TaskPrinter();

    public static void main(String[] args) {
        ConsoleTaskManager taskManager = new ConsoleTaskManager(tasks, printer);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Введите команду:");
                String commandLine = reader.readLine();
                try {
                    taskManager.executeCommand(commandLine);
                } catch (IncorrectTaskException | TaskNotFoundException ex) {
                    log.error("Error in processing", ex);
                }
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            System.err.println("Произошла ошибка при вводе данных");
        }
    }
}
