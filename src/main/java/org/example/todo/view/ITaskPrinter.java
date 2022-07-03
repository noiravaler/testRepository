package org.example.todo.view;

import org.example.todo.data.Task;

import java.util.stream.Stream;

public interface ITaskPrinter {

    void print(Stream<Task> taskStream);
}
