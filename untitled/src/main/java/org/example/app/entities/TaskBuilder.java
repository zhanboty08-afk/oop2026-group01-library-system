package org.example.app.entities;

import java.time.LocalDate;

public class TaskBuilder {

    private Long projectId;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.TODO;
    private LocalDate deadline;
    private TaskType type = TaskType.GENERAL;

    public TaskBuilder projectId(Long projectId) { this.projectId = projectId; return this; }
    public TaskBuilder title(String title) { this.title = title; return this; }
    public TaskBuilder description(String description) { this.description = description; return this; }
    public TaskBuilder status(TaskStatus status) { this.status = status; return this; }
    public TaskBuilder deadline(LocalDate deadline) { this.deadline = deadline; return this; }
    public TaskBuilder type(TaskType type) { this.type = type; return this; }

    public Task build() {
        return new Task(projectId, title, description, status, deadline, type);
    }
}
