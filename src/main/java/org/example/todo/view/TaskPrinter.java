package org.example.todo.view;

import org.example.todo.data.Task;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class TaskPrinter implements ITaskPrinter {

    private static void printTask(Task task) {
        System.out.printf("%d. [%s] %s%n", task.getIndex(), task.isComplete() ? "x" : " ", task.getDescription());
    }

    @Override
    public void print(Stream<Task> taskStream) {
        taskStream.forEach(TaskPrinter::printTask);
    }
}
