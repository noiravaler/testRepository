package org.example.todo.exceptions;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(Long index) {
        super(String.format("Задача с индексом %d не найдена", index));
    }
}
