package org.example.app.repositories;

import org.example.app.entities.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    long save(Task task);
    Optional<Task> findById(long id);
    List<Task> findAllByProject(long projectId);
    void updateStatus(long taskId, String status);
}

