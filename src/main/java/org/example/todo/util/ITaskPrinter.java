package org.example.todo.util;

import org.example.todo.models.Task;

import java.util.stream.Stream;

public interface ITaskPrinter {

    void print(Stream<Task> taskStream);
}
