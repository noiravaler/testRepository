package org.example.todo.storage;

import org.example.todo.data.Task;

import java.util.List;

public interface CustomTaskDao {

    List<Task> find(String query, boolean isAll, Long owner);
}
