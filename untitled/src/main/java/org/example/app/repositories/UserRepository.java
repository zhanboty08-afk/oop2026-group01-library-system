package org.example.app.repositories;

import org.example.app.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    long save (User user);

    Optional<User> findById(long id);
    List<User> findAll();
}

