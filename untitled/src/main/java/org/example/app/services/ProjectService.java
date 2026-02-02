package org.example.app.services;

import org.example.app.entities.Project;
import org.example.app.exceptions.NotFoundException;
import org.example.app.repositories.ProjectRepository;

import java.util.List;

public class ProjectService {
    private final ProjectRepository projects;
    private final UserService userService;

    public ProjectService(ProjectRepository projects, UserService userService) {
        this.projects = projects;
        this.userService = userService;
    }

    public long createProject(long ownerId, String title, String description) {
        userService.getUser(ownerId);
        return projects.save(new Project(ownerId, title, description));
    }

    public Project getProject(long id) {
        return projects.findById(id).orElseThrow(() -> new NotFoundException("project not found: " + id));
    }

    public List<Project> listProjectsByOwner(long ownerId) {
        userService.getUser(ownerId);
        return projects.findAllByOwner(ownerId);
    }
}

