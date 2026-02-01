package org.example.app.repositories;

import org.example.app.entities.Project;
import java.util.List;

public interface ProjectRepository extends Repository<Project, Long> {
    List<Project> findAllByOwner(Long ownerId);
}


