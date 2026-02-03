package org.example.app.entities;

import java.util.ArrayList;
import java.util.List;


public class ProjectBuilder {

    private Long ownerId;
    private String title;
    private String description;

    private final List<Long> memberIds = new ArrayList<>();
    private final List<String> tags = new ArrayList<>();

    public ProjectBuilder ownerId(Long ownerId) { this.ownerId = ownerId; return this; }
    public ProjectBuilder title(String title) { this.title = title; return this; }
    public ProjectBuilder description(String description) { this.description = description; return this; }

    public ProjectBuilder addMember(Long userId) { this.memberIds.add(userId); return this; }
    public ProjectBuilder addTag(String tag) { this.tags.add(tag); return this; }

    public Project build() {
        return new Project(ownerId, title, description);
    }
}
