package org.example.todo.dao;

import org.example.todo.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskDao implements ITaskDao {
    List<Task> taskList = new ArrayList<>();
    private Long index = 0L;

    @Override
    public void add(String description) {
        index++;
        taskList.add(new Task(index, description));
    }

    @Override
    public void delete(Long index) {
        taskList.removeIf(t -> t.getIndex() == index);
    }

    @Override
    public void edit(Long index, String description) {
        taskList.stream().filter(t -> t.getIndex() == index).findAny().ifPresent(task -> task.setDescription(description));
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
