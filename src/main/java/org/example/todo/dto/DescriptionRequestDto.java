package org.example.todo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DescriptionRequestDto {

    @NotNull
    private String description;
}
