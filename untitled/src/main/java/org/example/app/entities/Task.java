package org.example.app.entities;

import java.time.LocalDate;

public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate deadline;

    private TaskType type = TaskType.GENERAL;

    public Task(long id, long projectId, String title, String description, TaskStatus status, LocalDate deadline) {}

    public Task(Long projectId, String title, String description, TaskStatus status, LocalDate deadline) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    public Task(Long projectId, String title, String description, TaskStatus status, LocalDate deadline, TaskType type) {
        this(projectId, title, description, status, deadline);
        this.type = (type == null) ? TaskType.GENERAL : type;
    }

    public Long getId() { return id; }
    public Long getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDate getDeadline() { return deadline; }
    public TaskType getType() { return type; }

    public void setId(Long id) { this.id = id; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public void setType(TaskType type) { this.type = (type == null) ? TaskType.GENERAL : type; }
}


