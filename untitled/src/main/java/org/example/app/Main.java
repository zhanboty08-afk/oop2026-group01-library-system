package org.example.app;

import org.example.app.repositories.*;
import org.example.app.repositories.jdbc.*;
import org.example.app.services.*;
import org.example.app.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {


        UserRepository userRepo = new JdbcUserRepository();
        ProjectRepository projectRepo = new JdbcProjectRepository();
        TaskRepository taskRepo = new JdbcTaskRepository();
        CommentRepository commentRepo = new JdbcCommentRepository();

        UserService userService = new UserService(userRepo);
        ProjectService projectService = new ProjectService(projectRepo, userService);
        TaskService taskService = new TaskService(taskRepo, projectService);
        CommentService commentService = new CommentService(commentRepo, taskService, userService);

        new ConsoleUI(userService, projectService, taskService, commentService).run();

    }
}

