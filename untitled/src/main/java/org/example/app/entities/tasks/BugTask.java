package org.example.app.entities.tasks;

import org.example.app.entities.Task;
import org.example.app.entities.TaskStatus;

import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(Long projectId, String title, String description, LocalDate deadline) {
        super(projectId, title, description, TaskStatus.TODO, deadline);
    }
}