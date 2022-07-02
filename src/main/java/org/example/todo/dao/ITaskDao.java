package org.example.todo.dao;

import org.example.todo.models.Task;

import java.util.stream.Stream;

public interface ITaskDao {

    void add(Task task);

    void delete(Long index);

    Stream<Task> find(String description, boolean isUncompleted);

    Task get(Long index);
}
