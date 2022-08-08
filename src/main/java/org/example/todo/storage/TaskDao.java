package org.example.todo.storage;

import org.example.todo.data.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskDao extends CrudRepository<Task, Long>, CustomTaskDao {

    @Modifying
    @Query("delete from Task t where t.id = ?1 and t.owner.id = ?#{ principal?.id }")
    int deleteOwnById(long id);


    @Query("from Task t where t.owner.id = ?#{ principal?.id }")
    Optional<Task> findOwnById(long id);
}
