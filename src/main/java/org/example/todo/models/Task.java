package org.example.todo.models;

import lombok.Data;

@Data
public class Task {
    private Long index = -1L;
    private boolean isComplete;
    private String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }
}
