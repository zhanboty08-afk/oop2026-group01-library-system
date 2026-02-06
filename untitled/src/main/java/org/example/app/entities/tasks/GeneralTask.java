package org.example.app.entities.tasks;

import org.example.app.entities.TaskType;
import java.time.LocalDate;

public class GeneralTask extends BaseTask {
    public GeneralTask(Long projectId, String title, String description, LocalDate deadline) {
        super(projectId, title, description, deadline);
    }

    @Override
    public TaskType getType() {
        return TaskType.GENERAL;
    }
}


