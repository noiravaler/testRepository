package org.example.todo.storage;

import org.example.todo.data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskDao implements ITaskDao {
    private static TaskDao instance = null;
    private final List<Task> taskList = new ArrayList<>();
    private Long index = 0L;

    private TaskDao() {

    }

    public static TaskDao getInstance() {
        if (instance == null) {
            instance = new TaskDao();
        }
        return instance;
    }

    @Override
    public void add(Task task) {
        index++;
        task.setIndex(index);
        taskList.add(task);
    }

    @Override
    public void delete(Long index) {
        taskList.removeIf(t -> t.getIndex() == index);
    }

    @Override
    public Stream<Task> find(String description, boolean isUncompleted) {
        Stream<Task> tasksStream = taskList.stream();
        if (isUncompleted) {
            tasksStream = tasksStream.filter(t -> !t.isComplete());
        }
        if (description != null && !description.isEmpty())
            tasksStream = tasksStream.filter(t -> t.getDescription().contains(description));
        return tasksStream;
    }

    @Override
    public Task get(Long index) {
        return taskList.stream().filter(t -> t.getIndex() == index).findFirst().orElse(null);
    }
}
