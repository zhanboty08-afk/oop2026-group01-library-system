package org.example.app;

import org.example.app.db.IDatabase;
import org.example.app.db.PostgresDatabase;
import org.example.app.repositories.*;
import org.example.app.repositories.jdbc.*;
import org.example.app.services.*;
import org.example.app.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        IDatabase db = new PostgresDatabase();

        UserRepository userRepo = new JdbcUserRepository(db);
        ProjectRepository projectRepo = new JdbcProjectRepository(db);
        TaskRepository taskRepo = new JdbcTaskRepository(db);
        CommentRepository commentRepo = new JdbcCommentRepository(db);

        UserService userService = new UserService(userRepo);
        ProjectService projectService = new ProjectService(projectRepo, userService);
        TaskService taskService = new TaskService(taskRepo, projectService);
        CommentService commentService = new CommentService(commentRepo, taskService, userService);

        new ConsoleUI(userService, projectService, taskService, commentService).run();
    }
}
