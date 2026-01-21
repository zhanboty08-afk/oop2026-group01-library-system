package org.example.app.services;

import org.example.app.entities.Task;
import org.example.app.entities.TaskStatus;
import org.example.app.exceptions.*;
import org.example.app.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository tasks;
    private final ProjectService projectService;

    public TaskService(TaskRepository tasks, ProjectService projectService) {
        this.tasks = tasks;
        this.projectService = projectService;
    }

    public long addTask(Long projectId, String title, String description, LocalDate deadline) {
        if (projectId == null) throw new TaskWithoutProjectException("Task must belong to a project");
        projectService.getProject(projectId);

        if (deadline != null && deadline.isBefore(LocalDate.now())) {
            throw new DeadlineInPastException("Deadline is in the past: " + deadline);
        }

        Task task = new Task(projectId, title, description, TaskStatus.TODO, deadline);
        return tasks.save(task);
    }

    public Task getTask(long id) {
        return tasks.findById(id).orElseThrow(() -> new NotFoundException("Task not found: " + id));
    }

    public List<Task> listTasks(long projectId) {
        projectService.getProject(projectId);
        return tasks.findAllByProject(projectId);
    }

    public void changeStatus(long taskId, TaskStatus newStatus) {
        Task task = getTask(taskId);

        TaskStatus cur = task.getStatus();
        boolean ok =
                (cur == TaskStatus.TODO && newStatus == TaskStatus.IN_PROGRESS) ||
                        (cur == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.DONE) ||
                        (cur == newStatus);

        if (!ok) {
            throw new NotFoundException("Not found: " + cur + " -> " + newStatus);
        }

        tasks.updateStatus(taskId, newStatus.name());
    }
}

