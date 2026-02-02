package org.example.app.entities.tasks;

import org.example.app.entities.*;

import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(Long projectId, String title, String description, TaskStatus status, LocalDate deadline) {
        super(projectId, title, description, status, deadline, TaskType.BUG);
    }
}
