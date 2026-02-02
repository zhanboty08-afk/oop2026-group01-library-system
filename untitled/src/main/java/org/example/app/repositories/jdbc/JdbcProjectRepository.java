package org.example.app.repositories.jdbc;

import org.example.app.db.DbManager;
import org.example.app.entities.Project;
import org.example.app.repositories.ProjectRepository;

import java.sql.*;
import java.util.*;

public class JdbcProjectRepository implements ProjectRepository {

    @Override
    public long save(Project project) {
        String sql = "insert into projects(owner_id, title, description) values (?, ?, ?) returning id";

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, project.getOwnerId());
            ps.setString(2, project.getTitle());
            ps.setString(3, project.getDescription());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Project> findById(Long id) {
        String sql = "select id, owner_id, title, description from projects where id = ?";

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(new Project(
                        rs.getLong("id"),
                        rs.getLong("owner_id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> findAll() {
        String sql = "select id, owner_id, title, description from projects order by id";
        List<Project> list = new ArrayList<>();

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> findAllByOwner(Long ownerId) {
        String sql = "select id, owner_id, title, description from projects where owner_id = ? order by id";
        List<Project> list = new ArrayList<>();

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, ownerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Project(
                            rs.getLong("id"),
                            rs.getLong("owner_id"),
                            rs.getString("title"),
                            rs.getString("description")
                    ));
                }
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




