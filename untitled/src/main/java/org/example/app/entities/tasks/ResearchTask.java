package org.example.app.entities.tasks;

import org.example.app.entities.TaskType;
import java.time.LocalDate;

public class ResearchTask extends BaseTask {
    public ResearchTask(Long projectId, String title, String description, LocalDate deadline) {
        super(projectId, title, description, deadline);
    }

    @Override
    public TaskType getType() {
        return TaskType.RESEARCH;
    }
}



