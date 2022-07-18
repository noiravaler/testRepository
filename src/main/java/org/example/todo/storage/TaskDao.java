package org.example.todo.storage;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.data.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class TaskDao implements ITaskDao {
    private final List<Task> taskList = new ArrayList<>();
    private Long index = 0L;

    @Override
    public void add(Task task) {
        log.debug("Добавление задачи с описанием {}", task.getDescription());
        index++;
        task.setIndex(index);
        taskList.add(task);
    }

    @Override
    public void delete(Long index) {
        log.debug("Удаление задачи с индексом {}", index);
        taskList.removeIf(t -> index.equals(t.getIndex()));
    }

    @Override
    public List<Task> find(String description, boolean isUncompleted) {
        Stream<Task> tasksStream = taskList.stream();
        if (isUncompleted) {
            tasksStream = tasksStream.filter(t -> !t.isComplete());
        }
        if (description != null && !description.isEmpty())
            tasksStream = tasksStream.filter(t -> t.getDescription().contains(description));
        return tasksStream.collect(Collectors.toList());
    }

    @Override
    public Task get(Long index) {
        return taskList.stream().filter(t -> index.equals(t.getIndex())).findFirst().orElse(null);
    }
}
