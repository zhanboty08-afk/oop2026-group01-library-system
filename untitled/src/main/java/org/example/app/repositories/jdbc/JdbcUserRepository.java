package org.example.app.repositories.jdbc;

import org.example.app.db.DbManager;
import org.example.app.entities.User;
import org.example.app.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    public JdbcUserRepository() {
    }

    public long save(User user) {
        String sql = "insert into users(name, email) values (?, ?) returning id";

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error save user: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    public Optional<User> findById(Long id) {
        String sql = "select id, name, email from users where id = ?";

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(
                        new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error find user: " + e.getMessage(), e);
        }
    }

    public List<User> findAll() {
        String sql = "select id, name, email from users order by id";
        List<User> list = new ArrayList<>();

        try (Connection con = DbManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("DB error list users: " + e.getMessage(), e);
        }
    }
}



