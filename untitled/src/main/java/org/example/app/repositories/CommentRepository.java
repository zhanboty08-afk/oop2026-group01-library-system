package org.example.app.repositories;

import org.example.app.entities.Comment;
import java.util.List;

public interface CommentRepository {
    long save(Comment comment);
    List<Comment> findAllByTask(long taskId);
}

