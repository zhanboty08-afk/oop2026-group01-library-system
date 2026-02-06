package org.example.app.components;

import org.example.app.services.CommentService;
import org.example.app.services.TaskService;

public class TaskTrackingComponent {
    public final TaskService taskService;
    public final CommentService commentService;

    public TaskTrackingComponent(TaskService taskService, CommentService commentService) {
        this.taskService = taskService;
        this.commentService = commentService;
    }
}

