package org.example.todo.storage;

import org.example.todo.data.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends CrudRepository<Task, Long>, CustomTaskDao {

}
