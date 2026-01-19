package org.example.app.repositories.jdbc;

import org.example.app.db.IDatabase;
import org.example.app.entities.Task;
import org.example.app.entities.TaskStatus;
import org.example.app.repositories.TaskRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTaskRepository implements TaskRepository {
    private final IDatabase db;

    public JdbcTaskRepository(IDatabase db) { this.db = db; }

    @Override
    public long save(Task task) {
        String sql = "insert into tasks(project_id, title, description, status, deadline) values (?, ?, ?, ?, ?) returning id";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, task.getProjectId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getDescription());
            ps.setString(4, task.getStatus().name());
            if (task.getDeadline() == null) ps.setNull(5, Types.DATE);
            else ps.setDate(5, Date.valueOf(task.getDeadline()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException("DB error save task: " + e.getMessage(), e);
        }
    }

    public Optional<Task> findById(long id) {
        String sql = "select id, project_id, title, description, status, deadline from tasks where id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();

            Date d = rs.getDate("deadline");
            LocalDate deadline = (d == null) ? null : d.toLocalDate();

            return Optional.of(new Task(
                    rs.getLong("id"),
                    rs.getLong("project_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    TaskStatus.valueOf(rs.getString("status")),
                    deadline
            ));
        } catch (SQLException e) {
            throw new RuntimeException("DB error find task: " + e.getMessage(), e);
        }
    }
    public List<Task> findAllByProject(long projectId) {
        String sql = "select id, project_id, title, description, status, deadline from tasks where project_id = ? order by id";
        List<Task> list = new ArrayList<>();
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date d = rs.getDate("deadline");
                LocalDate deadline = (d == null) ? null : d.toLocalDate();

                list.add(new Task(
                        rs.getLong("id"),
                        rs.getLong("project_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskStatus.valueOf(rs.getString("status")),
                        deadline
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("DB error list tasks: " + e.getMessage(), e);
        }
    }

    public void updateStatus(long taskId, String status) {
        String sql = "update tasks set status = ? where id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setLong(2, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB error update status: " + e.getMessage(), e);
        }
    }
}

