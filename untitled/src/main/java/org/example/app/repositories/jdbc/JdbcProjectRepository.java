package org.example.app.repositories.jdbc;

import org.example.app.db.IDatabase;
import org.example.app.entities.Project;
import org.example.app.repositories.ProjectRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProjectRepository implements ProjectRepository {
    private final IDatabase db;

    public JdbcProjectRepository(IDatabase db) { this.db = db; }

    public long save(Project project) {
        String sql = "insert into projects(owner_id, title, description) values (?, ?, ?) returning id";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, project.getOwnerId());
            ps.setString(2, project.getTitle());
            ps.setString(3, project.getDescription());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException("DB error  save project: " + e.getMessage(), e);

        }
    }

    public Optional<Project> findById(long id) {
        String sql = "select id, owner_id, title, description from projects where id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(new Project(
                    rs.getLong("id"),
                    rs.getLong("owner_id"),
                    rs.getString("title"),
                    rs.getString("description")
            ));
        } catch (SQLException e) {
            throw new RuntimeException("DB error find project: " + e.getMessage(), e);
        }
    }

    public List<Project> findAllByOwner(long ownerId) {
        String sql = "select id, owner_id, title, description from projects where owner_id = ? order by id";
        List<Project> list = new ArrayList<>();
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Project(
                        rs.getLong("id"),
                        rs.getLong("owner_id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("DB error list projects: " + e.getMessage(), e);
        }
    }
}

