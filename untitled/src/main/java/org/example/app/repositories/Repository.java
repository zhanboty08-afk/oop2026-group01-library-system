package org.example.app.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    long save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}
