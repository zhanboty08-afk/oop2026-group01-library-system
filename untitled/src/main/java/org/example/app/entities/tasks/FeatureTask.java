package org.example.app.entities.tasks;

import org.example.app.entities.*;

import java.time.LocalDate;

public class FeatureTask extends Task {
    public FeatureTask(Long projectId, String title, String description, TaskStatus status, LocalDate deadline) {
        super(projectId, title, description, status, deadline, TaskType.FEATURE);
    }
}
