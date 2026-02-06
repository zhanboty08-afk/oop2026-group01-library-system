package org.example.app.repositories;

import org.example.app.entities.Task;
import java.util.List;

public interface TaskRepository extends Repository<Task, Long> {
    List<Task> findAllByProject(Long projectId);
    void updateStatus(long taskId, String status);
}


