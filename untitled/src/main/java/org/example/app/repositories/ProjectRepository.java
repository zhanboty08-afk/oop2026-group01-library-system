package org.example.app.repositories;

import org.example.app.entities.Project;
import java.util.List;
import java.util.Optional;



public interface ProjectRepository {
    long save(Project project);
    Optional<Project> findById(long id);
    List<Project> findAllByOwner(long ownerId);
}

