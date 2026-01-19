package org.example.app.services;

import org.example.app.entities.User;
import org.example.app.exceptions.NotFoundException;
import org.example.app.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) { this.users = users; }

    public long createUser(String name, String email) {
        return users.save(new User(name, email));
    }

    public User getUser(long id) {
        return users.findById(id).orElseThrow(() -> new NotFoundException("User not found: " + id));
    }

    public List<User> listUsers() {
        return users.findAll();
    }
}

