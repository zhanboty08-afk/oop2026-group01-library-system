package org.example.app.entities;

public class Project {
    private Long id;
    private Long ownerId;
    private String title;
    private String description;

    public Project(long ownerId, String title, String description) {}

    public Project(Long id, Long ownerId, String title, String description) {
        this.id = id; this.ownerId = ownerId; this.title = title; this.description = description;
    }

    public Long getId() { return id; }
    public Long getOwnerId() { return ownerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
}
