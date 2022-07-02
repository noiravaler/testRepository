package org.example.todo.models;

import lombok.Data;

@Data
public class Task {
    private Long index;
    private boolean isComplete;
    private String description;

    public Task(Long index, String description) {
        this.isComplete = false;
        this.index = index;
        this.description = description;
    }
}
