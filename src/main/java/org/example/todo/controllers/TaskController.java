package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.data.Task;
import org.example.todo.dto.DescriptionRequestDto;
import org.example.todo.dto.StatusRequestDto;
import org.example.todo.storage.TaskDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskDao tasks;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody DescriptionRequestDto request) {
        Task newTask = new Task(request.getDescription());
        tasks.save(newTask);
        return newTask;
    }

    @GetMapping
    public List<Task> getTaskList(@RequestParam(value = "search_string", required = false) String searchString,
                                  @RequestParam(value = "is_all", required = false) String isAll) {
        return tasks.find(searchString, "true".equals(isAll));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTaskDescription(@PathVariable("id") long id,
                                                        @Valid @RequestBody DescriptionRequestDto request) throws TaskNotFoundException {
        return taskById(id, t -> t.setDescription(request.getDescription()));
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<String> updateTaskStatus(@PathVariable("id") long id,
                                                   @Valid @RequestBody StatusRequestDto request) throws TaskNotFoundException {
        return taskById(id, t -> t.setComplete(request.getIsDone()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) throws TaskNotFoundException {
        return taskById(id, t -> tasks.deleteById(id));
    }

    private ResponseEntity<String> taskById(long id, Consumer<Task> taskConsumer) throws TaskNotFoundException {
        Optional<Task> task = tasks.findById(id);
        if (task.isPresent()) {
            Task t = task.get();
            taskConsumer.accept(t);
            tasks.save(t);
            return ResponseEntity.noContent().build();
        } else {
            throw new TaskNotFoundException(id);
        }
    }
}
