package org.example.app.components;

import org.example.app.services.ProjectService;
import org.example.app.services.UserService;

public class ProjectManagementComponent {
    public final UserService userService;
    public final ProjectService projectService;

    public ProjectManagementComponent(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }
}

