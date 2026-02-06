package org.example.app.entities.tasks;

import org.example.app.entities.TaskStatus;
import org.example.app.entities.TaskType;
import java.time.LocalDate;

public abstract class BaseTask {
    protected Long id;
    protected Long projectId;
    protected String title;
    protected String description;
    protected TaskStatus status = TaskStatus.TODO;
    protected LocalDate deadline;

    protected BaseTask(Long projectId, String title, String description, LocalDate deadline) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public abstract TaskType getType();

    public Long getId() { return id; }
    public Long getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDate getDeadline() { return deadline; }

    public void setId(Long id) { this.id = id; }
    public void setStatus(TaskStatus status) { this.status = status; }
}


