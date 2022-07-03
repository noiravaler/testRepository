package org.example.todo.storage;

import org.example.todo.data.Task;

import java.util.stream.Stream;

public interface ITaskDao {

    void add(Task task);

    void delete(Long index);

    Stream<Task> find(String description, boolean isUncompleted);

    Task get(Long index);
}
