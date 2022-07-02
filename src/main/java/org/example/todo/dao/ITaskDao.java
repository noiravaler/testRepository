package org.example.todo.dao;

import org.example.todo.models.Task;

import java.util.stream.Stream;

public interface ITaskDao {

    void add(String description);

    void delete(Long index);

    void edit(Long index, String description);

    Stream<Task> find(String description, boolean isUncompleted);

    Task get(Long index);
}
