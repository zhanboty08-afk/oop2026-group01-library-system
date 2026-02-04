package org.example.app.factories;

import org.example.app.entities.*;
import org.example.app.entities.tasks.*;

import java.time.LocalDate;

public final class TaskFactory {
    private TaskFactory() {}

    public static Task create(TaskType type, Long projectId, String title, String description, LocalDate deadline) {

        TaskStatus status = TaskStatus.TODO;

        return switch (type) {
            case BUG -> new BugTask(projectId, title, description, status, deadline);
            case FEATURE -> new FeatureTask(projectId, title, description, status, deadline);
            case RESEARCH -> new ResearchTask(projectId, title, description, status, deadline);
            default -> new Task(projectId, title, description, status, deadline, TaskType.GENERAL);
        };
    }

}

