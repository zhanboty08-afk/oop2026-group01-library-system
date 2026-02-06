package org.example.app.factories;

import org.example.app.entities.TaskType;
import org.example.app.entities.tasks.*;

import java.time.LocalDate;

public final class TaskFactory {
    private TaskFactory() {}

    public static BaseTask create(TaskType type, Long projectId, String title, String description, LocalDate deadline) {
        return switch (type) {
            case BUG -> new BugTask(projectId, title, description, deadline);
            case FEATURE -> new FeatureTask(projectId, title, description, deadline);
            case RESEARCH -> new ResearchTask(projectId, title, description, deadline);
            default -> new GeneralTask(projectId, title, description, deadline);
        };
    }
}


