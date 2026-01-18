package org.example.app.entities;

import java.time.LocalDate;

public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate deadline;

    public Task() {}

    public Task(Long id, Long projectId, String title, String description, TaskStatus status, LocalDate deadline) {
        this.id = id; this.projectId = projectId; this.title = title; this.description = description;
        this.status = status; this.deadline = deadline;
    }

    public Task(Long projectId, String title, String description, TaskStatus status, LocalDate deadline) {
        this.projectId = projectId; this.title = title; this.description = description;
        this.status = status; this.deadline = deadline;
    }

    public Long getId() { return id; }
    public Long getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDate getDeadline() { return deadline; }

    public void setId(Long id) { this.id = id; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}

