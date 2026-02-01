package org.example.app.repositories.jdbc;

import org.example.app.entities.Comment;
import org.example.app.repositories.CommentRepository;

import org.example.app.db.DbManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCommentRepository implements CommentRepository {

    public JdbcCommentRepository() {
    }

    public long save(Comment comment) {
        String sql = "insert into comments(task_id, author_id, text) values (?, ?, ?) returning id";

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, comment.getTaskId());
            ps.setLong(2, comment.getAuthorId());
            ps.setString(3, comment.getText());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error save comment: " + e.getMessage(), e);
        }
    }

    public List<Comment> findAllByTask(long taskId) {
        String sql = "select id, task_id, author_id, text from comments where task_id = ? order by id";

        List<Comment> list = new ArrayList<>();

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, taskId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Comment(
                            rs.getLong("id"),
                            rs.getLong("task_id"),
                            rs.getLong("author_id"),
                            rs.getString("text")
                    ));
                }
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("DB error list comments: " + e.getMessage(), e);
        }
    }
}


