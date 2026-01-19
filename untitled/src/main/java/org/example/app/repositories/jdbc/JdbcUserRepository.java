package org.example.app.repositories.jdbc;

import org.example.app.db.IDatabase;
import org.example.app.entities.User;
import org.example.app.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {
    private final IDatabase db;

    public JdbcUserRepository(IDatabase db) { this.db = db; }

    @Override
    public long save(User user) {
        String sql = "insert into users(name, email) values (?, ?) returning id";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException("DB error save user: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(long id) {
        String sql = "select id, name, email from users where id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(new User(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
        } catch (SQLException e) {
            throw new RuntimeException("DB error find user: " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select id, name, email from users order by id";
        List<User> list = new ArrayList<>();
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("DB error list users: " + e.getMessage(), e);
        }
    }
}


