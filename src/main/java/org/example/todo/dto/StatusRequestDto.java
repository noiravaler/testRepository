package org.example.todo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusRequestDto {

    @NotNull
    private Boolean isDone;
}
