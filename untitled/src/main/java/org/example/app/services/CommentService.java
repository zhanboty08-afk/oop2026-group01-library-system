package org.example.app.services;

import org.example.app.entities.Comment;
import org.example.app.exceptions.NotFoundException;
import org.example.app.repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository comments;
    private final TaskService taskService;
    private final UserService userService;

    public CommentService(CommentRepository comments, TaskService taskService, UserService userService) {
        this.comments = comments;
        this.taskService = taskService;
        this.userService = userService;
    }

    public long addComment(long taskId, long authorId, String text) {
        taskService.getTask(taskId);
        userService.getUser(authorId);
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Comment text is empty");
        return comments.save(new Comment(taskId, authorId, text));
    }

    public List<Comment> listComments(long taskId) {
        taskService.getTask(taskId);
        return comments.findAllByTask(taskId);
    }
}

