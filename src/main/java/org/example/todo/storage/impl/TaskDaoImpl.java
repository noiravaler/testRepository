package org.example.todo.storage.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.example.todo.data.Task;
import org.example.todo.storage.CustomTaskDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskDaoImpl implements CustomTaskDao {
    private final EntityManager entityManager;

    @Override
    public List<Task> find(String query, boolean isAll, Long owner) {
        StringBuilder jpql = new StringBuilder("from Task t ");
        List<String> conditions = new ArrayList<>();
        if (Strings.isNotBlank(query))
            conditions.add("t.description like :desc");
        if (!isAll)
            conditions.add("t.complete <> true");
        conditions.add("t.owner = " + owner);
        jpql.append("where ").append(String.join(" and ", conditions));
        TypedQuery<Task> taskQuery = entityManager.createQuery(jpql.toString(), Task.class);
        if (Strings.isNotBlank(query))
            taskQuery.setParameter("desc", "%" + query + "%");
        return taskQuery.getResultList();
    }
}
