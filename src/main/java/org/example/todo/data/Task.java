package org.example.todo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    private boolean complete;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner", nullable = false, updatable = false)
    @JsonIgnore
    private User owner;

    public Task(String description) {
        this.description = description;
    }
}
